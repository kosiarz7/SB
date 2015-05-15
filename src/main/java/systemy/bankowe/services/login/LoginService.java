package systemy.bankowe.services.login;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Optional;

import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.user.IUserDao;

/**
 * Serwis obsługujący operacje związane z loginem użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class LoginService implements ILoginService, Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 4057508273426430331L;
    /**
     * Pierwszy login użytkownika.
     */
    private static final String FIRST_LOGIN = "1000000";
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    /**
     * DAO dla użytkownika.
     */
    private IUserDao userDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNewLogin() {
        Optional<String> extremeLogin = userDao.getExtremeLogin();
        String newLogin = extremeLogin.isPresent() ? incrementLogin(extremeLogin.get()) : FIRST_LOGIN;
        LOGGER.debug("getNewLogin|Został stworzony nowy login: {}", newLogin);
        return newLogin;
    }
    
    /**
     * Inkrementuje login.
     * 
     * @param login login.
     * @return zinkrementowany login.
     */
    private String incrementLogin(final String login) {
        return new BigInteger(login).add(BigInteger.ONE).toString();
    }

    /**
     * Ustawia DAO użytkownika.
     * 
     * @param userDao DAO użytkownika.
     */
    public void setUserDao(final IUserDao userDao) {
        this.userDao = userDao;
    }
}
