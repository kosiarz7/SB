package systemy.bankowe.flows.addnextaccount;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

import javax.faces.application.FacesMessage;

import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.util.URLCoder;

public class AddNextAccountBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -940771596581534154L;
    /**
     * Serwis dla użytkownika.
     */
    private IUserService userService;
    
    private SpringSecurityContextUtil loginUserUtil;

    /**
     * Dodaje kolejne konto użytkownikowi.
     * 
     * @param account dane następnego konta.
     * @throws UnsupportedEncodingException 
     */
    public void addNextAccount(NextAccount account) throws UnsupportedEncodingException {
        Optional<UserData> userData = loginUserUtil.getLoggedInUser();
        if (userData.isPresent()) {
            if (userService.addNextAccount(account.getName(), userData.get(),
                    new Random().nextDouble() * 5000.0 + 200.45)) {
                account.setLevel(FacesMessage.SEVERITY_INFO);
                account.setMessage(URLCoder.encode("Nowe konto zostało dodane."));
            }
            else {
                account.setLevel(FacesMessage.SEVERITY_ERROR);
                account.setMessage(URLCoder
                        .decode("Wystąpił błąd podczas próby dodania następnego konta. Spróbuj później."));
            }
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setLoginUserUtil(SpringSecurityContextUtil loginUserUtil) {
        this.loginUserUtil = loginUserUtil;
    }
}
