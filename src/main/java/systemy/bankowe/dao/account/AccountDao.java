package systemy.bankowe.dao.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;

public class AccountDao extends HibernateUtil implements IAccountDao, Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    /**
     * Zapytanie zwracające numer konta bankowego, które zostało stworzone jako ostatnie.
     */
    private static final String GET_LAST_ACCOUNT_NUMBER = "from AccountDto a order by substring(a.number, 3) DESC";

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Optional<String> getLastCreatedAccountNumber() {
        Session session = openSession();
        Transaction tx = null;
        List<AccountDto> accounts = null;
        
        try {
            tx = session.beginTransaction();
            accounts = session.createQuery(GET_LAST_ACCOUNT_NUMBER).setMaxResults(1).list();
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
        
        Optional<String> accountNumber;
        if (accounts.isEmpty()) {
            accountNumber = Optional.empty();
            LOGGER.debug("getLastCreatedAccountNumber|Brak kont w bazie.");
        }
        else {
            accountNumber = Optional.of(accounts.get(0).getNumber());
            LOGGER.debug("getLastCreatedAccountNumber|Numer ostatnio stworzonego numeru konta: {}", accounts.get(0)
                    .getNumber());
        }
        return accountNumber;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public AccountDto getAccountByNumber(String accountNumber) {
        LOGGER.debug("getAccountByNumber|Numer konta: {}", accountNumber);
        
        Session session = openSession();
        Transaction tx = null;
        List<AccountDto> accounts = null;
        
        try {
            tx = session.beginTransaction();
            accounts = session.createQuery("from AccountDto where number = :number").setString("number", accountNumber)
                    .list();
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
        
        AccountDto account = null;
        if (accounts.isEmpty()) {
            LOGGER.debug("getAccountByNumber|Brak konta o numerze: {}", accountNumber);
        }
        else {
            account = accounts.get(0);
            LOGGER.debug("getAccountByNumber|Pobrano obiekt konta o numerze: {}", accountNumber);
        }
        
        return account;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AccountDto> getUserAccounts(UserDto userDto) {
        Session session = openSession();
        Transaction tx = null;
        List<AccountDto> accounts = null;
        
        try {
            tx = session.beginTransaction();
            accounts = session.createQuery("select a from AccountDto a join a.owners u where u.id = :id and enabled = true")
                    .setInteger("id", userDto.getId()).list();
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
        
        return null == accounts ? new ArrayList<>() : accounts;
    }
}