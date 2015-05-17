package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;

public class MainWindowBean implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
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
    
    /**
     * Pobiera informacje o kontach należących do zalogowanego użytkownika.
     * 
     * @return informacje o kontach.
     */
    public List<AccountStub> getAccounts() {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        List<AccountDto> accounts = user.isPresent() ? user.get().getUserDto().getAccounts() : new ArrayList<>();
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
     * Zamyka żądany rachunek.
     * 
     * @param data dane.
     */
    public void deleteAccount(final MainWindowData data) {
        // TODO dodać operację przelewu
        userService.closeAccount(data.getAccount().getNumber());
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
}