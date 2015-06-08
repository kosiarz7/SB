package systemy.bankowe.flows.addcoowner;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.UserData;

public class AddCoownerBean implements Serializable {

    private static final long serialVersionUID = 1073064360076814556L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddCoownerBean.class);

    private IUserDao userDao;

    private SpringSecurityContextUtil springSecurityUtil;
    
    private CommonDao<UserDto> commonUserDao;
    
    private IAccountDao accountDao;

    public void searchCoowner(final AddCoownerDataBean data) {
        LOGGER.debug("searchCoownerData|{}", data);
        String pesel = data.getPesel() != null ? data.getPesel().trim() : "";
        UserDto userDto = userDao.findUserByPesel(pesel);
        if (userDto != null) {
            Optional<UserData> userData = springSecurityUtil.getLoggedInUser();
            if (userData.isPresent()) {
                if (pesel.equals(userData.get().getUserDto().getPesel())) {
                    data.setUserExist(false);
                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Nie można dodać siebie jako współwłaściciela konta.",
                                    "Nie można dodać siebie jako współwłaściciela konta."));
                }
                else {
                    data.setUserExist(true);
                    data.setUserData(userDto);
                    LOGGER.debug("searchCoownerData|Załadowana dane użytkownika o numerze pesel: {}", pesel);
                }
            }
            else {
                throw new IllegalArgumentException("Użytkownik jest niezalogowany.");
            }
        }
        else {
            data.setUserExist(false);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Brak osoby o zadanym numerze pesel.",
                            "Brak osoby o zadanym numerze pesel."));
            LOGGER.debug("searchCoownerData|Brak użytkownika o numerze pesel: {}", pesel);
        }
    }

    public void addCoowner(final AddCoownerDataBean data, final String accountNumber) {
        LOGGER.debug("addCoowner|NRB: {} data: {}", accountNumber, data);
        data.setMsg("Współwłaściciel został dodany.");
        AccountDto accountDto = accountDao.getAccountByNumber(accountNumber);
        data.getUserData().getAccounts().add(accountDto);
        commonUserDao.update(data.getUserData());
    }
    
    public void setCommonUserDao(CommonDao<UserDto> commonUserDao) {
        this.commonUserDao = commonUserDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
        this.springSecurityUtil = springSecurityUtil;
    }
}
