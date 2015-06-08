package systemy.bankowe.flows.cyclictransfer;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.CyclicTransferDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.util.StringUtil;

public class CyclicTransferBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -829694990955208898L;
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicTransferBean.class);

    private IAccountNumberService accountNumberService;

    private CommonDao<CyclicTransferDto> commonDao;

    private SpringSecurityContextUtil userUtil;

    private IUserService userService;
    
    private IAccountDao accountDao;

    public void init(final CyclicTransferDataBean data) {
        data.setDefindedCyclicTransfers(userUtil.getAllCyclicTransfers());
        Optional<UserData> user = userUtil.getLoggedInUser();
        if (user.isPresent()) {
            setDebitAccounts(data, userService.getUserAccounts(user.get()));
        }
    }

    /**
     * Ustawia konta użytkownika jako dostępne konta dłużnika.
     * 
     * @param transferData dane
     * @param userAccounts lista kont użytkownika.
     */
    private void setDebitAccounts(CyclicTransferDataBean data, List<AccountDto> userAccounts) {
        List<String> accounts = userAccounts.stream()
                .map(account -> accountNumberService.formatAccountNumber(account.getNumber()))
                .collect(Collectors.toList());
        data.setAvailableDebitAccounts(accounts);
        if (accounts.size() > 0) {
            data.setDebitAccount(accounts.get(0));
        }
    }

    public boolean validateCyclicTransfer(final CyclicTransferDataBean data) {
        if (!validateAccountNumber(data.getAccountNumber())) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny numer konta.",
                            "Niepoprawny numer konta."));
            return false;
        }
        if (data.getAmount() < 0.0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kwota przelewu nie może być ujemnna.",
                            "Kwota przelewu nie może być ujemnna."));
            return false;
        }
        if (data.getDayOfMonth() < 1 || data.getDayOfMonth() > 28) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dzień przelewu musi się mieścic w zakresie [1,28].",
                            "Dzień przelewu musi się mieścic w zakresie [0,28]."));
            return false;
        }

        return true;
    }

    private boolean validateAccountNumber(String accountNumber) {
        String account = StringUtil.removeWhitespaces(accountNumber);
        return accountNumberService.isValid(account);
    }

    public void addNewCyclicTransfer(final CyclicTransferDataBean data) {
        try {
            commonDao.save(createDto(data));
            data.setMessage("Przelew cykliczny został dodany.");
        }
        catch (Exception e) {
            LOGGER.error("addNewCyclicTransfer|Wystąpił błąd podczas próby dodania nowego przelewu cyklicznego.", e);
            data.setMessage("Wystąpił bład podczas dodawania nowego przelewu cyklicznego. Spróbuj później.");
        }
    }

    private CyclicTransferDto createDto(final CyclicTransferDataBean data) {
        CyclicTransferDto cyclicTransferDto = new CyclicTransferDto();
        fillFields(data, cyclicTransferDto);
        return cyclicTransferDto;
    }

    private void fillFields(final CyclicTransferDataBean data, final CyclicTransferDto cyclicTransferDto) {
        cyclicTransferDto.setAccountNumber(accountNumberService.removeWhitespaces(data.getAccountNumber()));
        cyclicTransferDto.setName(data.getName());
        cyclicTransferDto.setCity(data.getCity());
        cyclicTransferDto.setStreet(data.getStreet());
        cyclicTransferDto.setStreetNo(data.getStreetNo());
        cyclicTransferDto.setZipcode(data.getZipcode());
        cyclicTransferDto.setUser(getCurrentUserDto());
        cyclicTransferDto.setAmount(data.getAmount());
        cyclicTransferDto.setDayOfMonth(data.getDayOfMonth());
        cyclicTransferDto.setDebitAccount(accountDao.getAccountByNumber(accountNumberService.removeWhitespaces(data
                .getDebitAccount())));
    }

    private UserDto getCurrentUserDto() {
        Optional<UserData> user = userUtil.getLoggedInUser();
        if (user.isPresent()) {
            return user.get().getUserDto();
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }

    public void copyDataToEdit(final CyclicTransferDataBean data) {
        CyclicTransferDto dto = data.getSelectedDto();
        data.setAccountNumber(accountNumberService.formatAccountNumber(dto.getAccountNumber()));
        data.setCity(dto.getCity());
        data.setName(dto.getName());
        data.setStreet(dto.getStreet());
        data.setStreetNo(dto.getStreetNo());
        data.setZipcode(dto.getZipcode());
        data.setAmount(dto.getAmount());
        data.setDayOfMonth(dto.getDayOfMonth());
    }

    public void saveChanges(final CyclicTransferDataBean data) {
        try {
            CyclicTransferDto dto = data.getSelectedDto();
            fillFields(data, dto);
            commonDao.update(dto);
            data.setMessage("Zmiany zostały zapisane.");
        }
        catch (Exception e) {
            LOGGER.error("saveChanges|Wystąpił błąd podczas próby uaktualnienia danych przelewu cyklicznego.", e);
            data.setMessage("Wystąpił bład podczas próby zapisania zmian. Spróbuj później.");
        }
    }

    public void delete(final CyclicTransferDataBean data) {
        try {
            commonDao.delete(data.getSelectedDto());
            data.setMessage("Przelew cykliczny został usunięty.");
        }
        catch (Exception e) {
            LOGGER.error("delete|Wystąpił błąd podczas próby dodania nowego przelewu cyklicznego.", e);
            data.setMessage("Wystąpił bład podczas usunięcia przelewu cyklicznego. Spróbuj później.");
        }
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setCommonDao(CommonDao<CyclicTransferDto> commonDao) {
        this.commonDao = commonDao;
    }

    public void setUserUtil(SpringSecurityContextUtil userUtil) {
        this.userUtil = userUtil;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
