package systemy.bankowe.flows.addnextaccount;

import java.io.Serializable;

import systemy.bankowe.services.user.IUserService;

public class AddNextAccountBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -940771596581534154L;
    /**
     * Serwis dla użytkownika.
     */
    private IUserService userService;

    /**
     * Dodaje kolejne konto użytkownikowi.
     * 
     * @param account dane następnego konta.
     */
    public boolean addNextAccount(NextAccount account) {
        return userService.addNextAccount(account.getName());
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
