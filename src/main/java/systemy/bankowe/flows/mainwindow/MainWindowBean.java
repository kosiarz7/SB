package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
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
            accountStubs.add(new AccountStub().name(a.getName()).saldo(a.getSaldo()).number(a.getNumber()));
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

    public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
        this.springSecurityUtil = springSecurityUtil;
    }
}