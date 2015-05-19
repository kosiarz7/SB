package systemy.bankowe.dao.user;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositDto;

/**
 * DAO dla użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class UserDao extends HibernateUtil implements IUserDao, Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = -6299204458673721070L;
    /**
     * Zapytanie pobierające użytkoników posortowanych względem loginu.
     */
    private static final String GET_USERS_ORDER_BY_LOGIN = "from UserDto u order by u.login DESC";
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public UserDto loadUserByUserName(final String login) {
        LOGGER.debug("loadUserByUserName|Próba pobrania użytkownika o loginie: {}", login);
        UserDto userDto = null;

        Session session = openSession();
        Transaction tx = null;
        List<UserDto> users = null;

        try {
            tx = session.beginTransaction();
            users = session.createQuery("from UserDto where login = :login").setString("login", login).list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }

        if (users.isEmpty()) {
            userDto = new UserDto();
            LOGGER.debug("loadUserByUserName|Brak użytkownika o nazwie: {}", login);
        }
        else {
            userDto = users.get(0);
            LOGGER.debug("loadUserByUserName|Załadowane dane użytkownika: {}", login);
        }

        return userDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(final UserDto userDto) {
        LOGGER.debug("updateUser|Uaktualnienie użytkownika: {}", userDto);

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(userDto);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Optional<String> getExtremeLogin() {
        Session session = openSession();
        Transaction tx = null;
        List<UserDto> users = null;

        try {
            tx = session.beginTransaction();
            users = session.createQuery(GET_USERS_ORDER_BY_LOGIN).setMaxResults(1).list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }

        Optional<String> login;
        if (users.isEmpty()) {
            login = Optional.empty();
            LOGGER.debug("getExtremeLogin|Brak użytkowników w bazie.");
        }
        else {
            login = Optional.of(users.get(0).getLogin());
            LOGGER.debug("getExtremeLogin|Maksymalny login: '{}' został załadowany.", users.get(0).getLogin());
        }

        return login;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccount(final UserDto userDto, final AccountDto accountDto) {
        LOGGER.debug("addAccount|Próba dodania konta: {} do użytkownika: {}", accountDto, userDto);
        
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            userDto.getAccounts().add(accountDto);
            session.update(userDto);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    @Override
    public void addDeposit(final UserDto userDto, final DepositDto deposit) {
        LOGGER.debug("addDeposit|Pr�ba dodania lokaty: {} do u�ytkownika: {}", deposit, userDto);
        
        Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            userDto.getDeposits().add(deposit);
            session.update(userDto);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            session.close();
        }
    }
}
