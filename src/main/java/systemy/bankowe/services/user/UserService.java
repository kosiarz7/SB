package systemy.bankowe.services.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;

/**
 * Usługi związane z użytkownikiem.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class UserService implements IUserService, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -968298866296529495L;
    /**
     * DAO dla użytkownika.
     */
    private IUserDao userDao;
    /**
     * Serwis obsługujący numery kont.
     */
    private IAccountNumberService accountNumberService;
    /**
     * Metody wspomagającę pracę z Spring Security.
     */
    private SpringSecurityContextUtil springSecurityUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserData loadUserByLogin(final String username) throws NoSuchUserException {
        UserDto userDto = userDao.loadUserByUserName(username);
        if (!userDto.isLoaded()) {
            throw new NoSuchUserException("Brak użytkownika o nazwie: " + username);
        }
        return new UserData(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetFailAttempts(final UserData userData) {
        UserDto userDto = userData.getUserDto();
        userDto.setFailAttempts(0);
        userDao.updateUser(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementFailAttempts(final UserData userData) {
        UserDto userDto = userData.getUserDto();
        userDto.setFailAttempts(userDto.getFailAttempts() + 1);
        userDao.updateUser(userDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addNextAccount(String accountName) {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();

        if (user.isPresent()) {
            AccountDto accountDto = new AccountDto();
            accountDto.setNumber(accountNumberService.createNewAccountNumber());
            accountDto.setName(accountName);
            accountDto.setSaldo(new Random().nextDouble() * 5000.0 + 200.45);
            accountDto.setSetupDate(new Date());
            userDao.addAccount(user.get().getUserDto(), accountDto);
            return true;
        }

        return false;
    }

    /**
     * Ustawia DAO użytkownika.
     * 
     * @param userDao dao użytkownika.
     */
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
        this.springSecurityUtil = springSecurityUtil;
    }

}
