package systemy.bankowe.flows.addaccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.role.IRoleDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.CitizenshipDto;
import systemy.bankowe.dto.RoleDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.encrypt.EncryptException;
import systemy.bankowe.services.encrypt.IEncryptor;
import systemy.bankowe.services.login.ILoginService;
import systemy.bankowe.services.mail.IMailService;
import systemy.bankowe.services.mail.Mail;

public class AddAccountBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -5385878275219085603L;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAccountBean.class);
    /**
     * Nazwa uprawnienia dla zwykłego użytkownika.
     */
    private static final String USER_ROLE_NAME = "USER";
    /**
     * Treść wiadomości powitalnej.
     */
    private static final String WELCOME_MSG = 
            "Witaj w StealingBank,\n\n"
            + "numer Twojego nowego konta: %s\n"
            + "login do Twojego konta: %s\n"
            + "hasło: podane podczas rejestracji.\n\n"
            + "Życzymy pomyślnych transakcji!";
    /**
     * Obiekt kodujący napisy.
     */
    private IEncryptor encryptor;
    /**
     * Serwis dla loginów użytkowników.
     */
    private ILoginService loginService;
    /**
     * Serwis dla numerów kont bankowych.
     */
    private IAccountNumberService accountNumberService;
    /**
     * DAO dla uprawnień.
     */
    private IRoleDao roleDao;
    /**
     * Serwis wysyłający mail.
     */
    private IMailService mailService;
    /**
     * Wspólne DAO dla usera.
     */
    private CommonDao<UserDto> commonUserDao;
    /**
     * DAO dla obywatelstwa.
     */
    private CommonDao<CitizenshipDto> citizenshipDao;
    
    
    /**
     * Pobiera listę wszystkich obywatelstw.
     * 
     * @return lista wszystkich obywatelstw.
     */
    public void setAllCitizenships(final NewAccountData newAccountData) {
        List<CitizenshipDto> citizensDto = citizenshipDao.getAll(CitizenshipDto.class);
        newAccountData.setCitizenshipsDto(citizensDto);
        newAccountData.setCitizenships(citizensDto.stream().map(c -> c.getCitizenship()).collect(Collectors.toList()));
    }

    /**
     * Sprawdza poprawność wprowadzonych danych.
     * 
     * @param newAccountData dane nowego konta.
     * @return true - dane są poprawne; false - dane nie są poprawne.
     */
    public boolean validateNewAccountData(final NewAccountData newAccountData) {
        boolean result = true;
        
        if (!arePasswordsEquals(newAccountData)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podane hasła są różne.", "Podane hasła są różne."));
            result = false;
        }
        if (!areEmailsEquals(newAccountData)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podane adresy mail są różne.",
                            "Podane adresy mail są różne."));
            result = false;
        }
        
        return result;
    }

    /**
     * Sprawdza czy wpisane hasła są takie same.
     * 
     * @param newAccountData dane nowego konta.
     * @return true - hasła są takie same; - false hasła są różne.
     */
    private boolean arePasswordsEquals(final NewAccountData newAccountData) {
        return newAccountData.getPassword().equals(newAccountData.getConfirmedPassword());
    }

    /**
     * Sprawdza czy wpisane adresy email są takie same.
     * 
     * @param newAccountData dane nowego konta.
     * @return true - adresy mail są takie same; false - adresy mail są różne.
     */
    private boolean areEmailsEquals(final NewAccountData newAccountData) {
        return newAccountData.getMail().equals(newAccountData.getConfirmedMail());
    }

    /**
     * Dodaje nowe konto użytkownika.
     * 
     * @param newAccountData dane nowego użytkownika.
     * @return
     */
    public boolean addAccount(final NewAccountData newAccountData) {
        LOGGER.debug("addAccount|Próba utworzenia nowego konta dla danych: {}", newAccountData);
        UserDto newUser = new UserDto();

        try {
            setEncryptedPassword(newUser, newAccountData);
            addUserRoles(newUser, newAccountData);
        }
        catch (EncryptException e) {
            LOGGER.error("addAccount|Wystąpił błąd podczas obliczania skrótu hasła.", e);
            return false;
        }
        catch (LackOfRoleException e) {
            LOGGER.error("addAccount|Nieudana próba pobrania uprawnień o nazwie: " + USER_ROLE_NAME, e);
            return false;
        }

        fillBasicUserData(newUser, newAccountData);
        fillUserData(newUser, newAccountData);
        fillAccountData(newUser, newAccountData);
        commonUserDao.save(newUser);
        try {
            sendWelcomeMessage(newUser);
        }
        catch (MessagingException e) {
            LOGGER.warn("addAccount|Wiadomość powitalna NIE została wysłana, jednak konto zostało utworzone.");
        }

        return true;
    }

    /**
     * Ustawia skrót hasła.
     * 
     * @param newUser nowy użytkownik.
     * @param newAccountData dane nowego użytkownika.
     * @throws EncryptException gdy wystąpiły błędy podczas kodowania.
     */
    private void setEncryptedPassword(final UserDto newUser, final NewAccountData newAccountData)
            throws EncryptException {
        newUser.setPassword(encryptor.encrypt(newAccountData.getPassword()));
    }

    /**
     * Wypełnia podstawowe dane użytkownika.
     * 
     * @param newUser nowy użytkownik.
     * @param newAccountData dane nowego użytkownika.
     */
    private void fillBasicUserData(final UserDto newUser, final NewAccountData newAccountData) {
        newUser.setName(newAccountData.getName());
        newUser.setSurname(newAccountData.getSurname());
        newUser.setLogin(loginService.getNewLogin());
    }

    /**
     * Wypełnia dane użytkownika.
     * 
     * @param newUser nowy użytkownik.
     * @param newAccountData dane nowego użytkownika.
     */
    private void fillUserData(final UserDto newUser, final NewAccountData newAccountData) {
        /*UserDataDto userData = new UserDataDto();
        userData.setCity(newAccountData.getCity());
        userData.setEmail(newAccountData.getMail());
        userData.setStreet(newAccountData.getStreet());
        userData.setStreetNo(newAccountData.getStreetNo());
        userData.setTelephoneNo(newAccountData.getTelephoneNo());
        userData.setZipcode(newAccountData.getZipcode());
        userData.setUser(newUser);
        List<UserDataDto> data = new ArrayList<UserDataDto>();
        data.add(userData);
        newUser.setUserData(data);*/
    }

    /**
     * Wypełnia dane konta bankowego.
     * 
     * @param newUser nowy użytkownik.
     * @param newAccountData dane nowego użytkownika.
     */
    private void fillAccountData(final UserDto newUser, final NewAccountData newAccountData) {
        AccountDto account = new AccountDto();
        account.setName(newAccountData.getAccountName());
        account.setNumber(accountNumberService.createNewAccountNumber());
        account.setSaldo(new Random().nextDouble() * 10_000.0 + 845.123);
        List<UserDto> users = new ArrayList<>();
        users.add(newUser);
        //account.setUsers(users);
        Set<AccountDto> accounts = new HashSet<>();
        accounts.add(account);
        //newUser.setAccounts(accounts);
    }

    /**
     * Dodaje uprawnienia użytkownika.
     * 
     * @param newUser nowy użytkownik.
     * @param newAccountData dane nowego użytkownika.
     */
    private void addUserRoles(final UserDto newUser, final NewAccountData newAccountData) throws LackOfRoleException {
        Optional<RoleDto> role = roleDao.getRoleByName(USER_ROLE_NAME);
        if (role.isPresent()) {
            List<RoleDto> roles = new ArrayList<>();
            roles.add(role.get());
            //newUser.setRoles(roles);
        }
        else {
            throw new LackOfRoleException("Brak uprawnienia o nazwie: " + USER_ROLE_NAME);
        }
    }
    
    /**
     * Wysyła powitalną wiadomość.
     * 
     * @param newUser dane nowego użytkownika.
     * @throws MessagingException gdy wystąpił błąd podczas wysyłania wiadomości.
     */
    private void sendWelcomeMessage(final UserDto newUser) throws MessagingException {
        Mail mail = new Mail();
        mail.setRecipient(newUser.getEmail());
        mail.setTitle("Witaj w StealingBank!");
        mail.setContent(createMsgContent(newUser));
        mailService.sendMail(mail);
    }
    
    /**
     * Tworzy zawartość wiadomości powitalnej.
     * 
     * @param newUser dane nowego użytkownika.
     * @return zawartość wiadoości powitalnej.
     */
    private String createMsgContent(final UserDto newUser) {
        return String.format(WELCOME_MSG, newUser.getAccounts().get(0).getNumber(), newUser.getLogin());
    }

    /**
     * Ustawia koder.
     * 
     * @param encryptor koder.
     */
    public void setEncryptor(IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /**
     * Ustawia serwis loginów.
     * 
     * @param loginService serwis loginów.
     */
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Ustawia serwis numerów kont bankowych.
     * 
     * @param accountNumberService serwis numerów kont bankowych.
     */
    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    /**
     * Ustawia DAO dla uprawnień.
     * 
     * @param roleDao DAO dla uprawnień.
     */
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Ustawia serwis wysyłający maile.
     * 
     * @param mailService serwis wysyłający maile.
     */
    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public void setCommonUserDao(CommonDao<UserDto> commonUserDao) {
        this.commonUserDao = commonUserDao;
    }

    public void setCitizenshipDao(CommonDao<CitizenshipDto> citizenshipDao) {
        this.citizenshipDao = citizenshipDao;
    }
}