package systemy.bankowe.services.user;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.UserDto;

/**
 * Usługi związane z użytkownikiem.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
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
     * Ustawia DAO użytkownika.
     * 
     * @param userDao dao użytkownika.
     */
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }
    
}