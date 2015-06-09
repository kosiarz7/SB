package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;

public class MainWindowBean implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindowBean.class);
    /**
     * Metody wspomagającę pracę z Spring Security.
     */
    private SpringSecurityContextUtil springSecurityUtil;
    /**
     * Serwis dla użytkownika.
     */
    private IUserService userService;
    /**
     * Serwis dla kont bankowych.
     */
    private IAccountNumberService accountNumberService;
    
    private CommonDao<UserDto> commonUserDao;
    
    private CommonDao<AccountDto> commonAccountDto;
    
    /**
     * Pobiera informacje o kontach należących do zalogowanego użytkownika.
     * 
     * @return informacje o kontach.
     */
    public List<AccountStub> getAccounts() {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        List<AccountDto> accounts = user.isPresent() ? user.get().getUserDto().getAccounts() : Collections.emptyList();
        return convertAccounts(accounts);
    }
    
    /**
     * Konwertuje obiekty DTO na obiekty pożądane na widoku.
     * 
     * @param accounts zbiór obiektów DTO.
     * @return lista obiektów pożądanych na widoku.
     */
    private List<AccountStub> convertAccounts(List<AccountDto> accounts) {
        List<AccountStub> accountStubs = new ArrayList<>();
        
        for (AccountDto a : accounts) {
            accountStubs.add(new AccountStub().name(a.getName()).saldo(a.getSaldo()).number(a.getNumber())
                    .coowners(a.getOwners().size() > 1));
        }
        
        return accountStubs;
    }
    
    /**
     * Zwraca numer wybranego rachunku bankowego.
     * 
     * @param data dane przepływu.
     * @return numer wybranego rachunku bankowego.
     */
    public String getSelectedAccountNumber(final MainWindowData data) {
        String accountNumber = "";
        
        if (data.getAccount() != null) {
            accountNumber = data.getAccount().getNumber();
        }
        
        return accountNumber;
    }
    
    /**
     * Sprawdza czy podany numer rachunku bankowego jets poprawny.
     * 
     * @param data dane.
     * @return true - nr rachunku jest poprawny; false = numer rachunku jest niepoprawny.
     */
    public boolean isAccountNumberValid(final MainWindowData data) {
        boolean result = accountNumberService.isValid(accountNumberService.removeWhitespaces(data.getAccountNumber()));
        if (!result) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podano nieprawidłowy numer rachunku bankowego.",
                            "Podano nieprawidłowy numer rachunku bankowego."));
        }
        return result;
    }
    
    /**
     * Ustawia szczegóły wybranego okna.
     * 
     * @param accountStub wybrane konto.
     * @param accountDetails szczegóły wybranego konta.
     */
    public void refreshAccountDetails(final AccountStub accountStub, final AccountDetails accountDetails) {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        
        if (user.isPresent()) {
            AccountDto selectedAccount = getSelectedAccount(user.get().getUserDto(), accountStub.getNumber());
            if (null == selectedAccount) {
                throw new IllegalArgumentException("Użytkownik o loginie: " + user.get().getUserDto().getLogin()
                        + " nie posiada konta o numerze: " + accountStub.getNumber()); 
            }
            else {
                commonAccountDto.refresh(selectedAccount);
                List<AccountOwnerDetails> owners = new ArrayList<>();
                for (UserDto u : selectedAccount.getOwners()) {
                    owners.add(new AccountOwnerDetails().address(u.getAddress()).city(u.getCity()).mail(u.getEmail())
                            .name(u.getName()).surename(u.getSurname()).zipcode(u.getZipcode()));
                }
                accountDetails.amount(selectedAccount.getSaldo()).name(selectedAccount.getName())
                        .number(selectedAccount.getNumber()).owners(owners);
            }
        }
        else {
            throw new IllegalStateException("Użytkownik nie jest zalogowany!");
        }
    }
    
    /**
     * Zwraca wybrane konto.
     * 
     * @param userDto dane użytkownika.
     * @param accountNumber numer wybranego konta.
     * @return wybrane konto.
     */
    private AccountDto getSelectedAccount(final UserDto userDto, final String accountNumber) {
        for (AccountDto a : userDto.getAccounts()) {
            if (accountNumber.equals(a.getNumber())) {
                return a;
            }
        }
        return null;
    }
    
    /**
     * Zamyka żądany rachunek.
     * 
     * @param data dane.
     */
    public void deleteAccount(final MainWindowData data) {
        // TODO dodać operację przelewu
        userService.closeAccount(data.getAccount().getNumber());
    }
    
    public void resign(final MainWindowData data) {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        if (user.isPresent()) {
            UserDto userDto = user.get().getUserDto();
            int index = -1, i = 0;
            for (AccountDto a : userDto.getAccounts()) {
                if (data.getAccount().getNumber().equals(a.getNumber())) {
                    index = i;
                }
                ++i;
            }
            if (index > -1) {
                userDto.getAccounts().remove(index);
                commonUserDao.update(userDto);
            }
            else {
                LOGGER.warn("resign|Użytkownik nie posiada konta o numerze: {}", data.getAccount().getNumber());
            }
        }
        else {
            throw new IllegalArgumentException("Użytkownik jest niezalogowany!");
        }
    }

    public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
        this.springSecurityUtil = springSecurityUtil;
    }
    
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setCommonUserDao(CommonDao<UserDto> commonUserDao) {
        this.commonUserDao = commonUserDao;
    }

    public void setCommonAccountDto(CommonDao<AccountDto> commonAccountDto) {
        this.commonAccountDto = commonAccountDto;
    }
}