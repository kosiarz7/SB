package systemy.bankowe.dao.cyclictransfer;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.CyclicTransferDto;
import systemy.bankowe.dto.UserDto;

public class CyclicTransferDao extends HibernateUtil implements ICyclicTransferDao, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 7599121799456058858L;
    @InjectLogger
    private static final Logger LOGGER = null;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CyclicTransferDto> getAllCyclicTransfers(UserDto userDto) {
        LOGGER.debug("getAllCyclicTransfers|ENTRY|UserDto: {}", userDto);
        
        Session session = openSession();
        Transaction tx = null;
        List<CyclicTransferDto> cyclicTransfers = null;
        
        try {
            tx = session.beginTransaction();
            cyclicTransfers = session.createQuery("from CyclicTransferDto where user = :user").setEntity("user", userDto)
                    .list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error(
                    "getAllCyclicTransfers|Wystąpił wyjątek podczas próby pobrania przelewów cyklicznych dla: {}",
                    userDto, e);
            throw e;
        }
        finally {
            session.close();
        }
        
        return null != cyclicTransfers ? cyclicTransfers : Collections.emptyList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCyclicTransfers(AccountDto debitAccount) {
        LOGGER.debug("removeCyclicTransfers|Usunięcie przelewów cyklicznych wychodzących z konta: {}",
                debitAccount.getNumber());
        Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.createQuery("delete CyclicTransferDto where debitAccount = :debitAccount")
                .setEntity("debitAccount", debitAccount).executeUpdate();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error(
                    "removeCyclicTransfers|Wystąpił wyjątek podczas próby usunięcie przelewów cyklicznych wychodzących z konta: {}",
                    debitAccount.getNumber(), e);
            throw e;
        }
        finally {
            session.close();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CyclicTransferDto> getAllCyclicTransfersFromDay(int dayOfMonth) {
        LOGGER.debug("getAllCyclicTransfersFromDay|Day of month: {}", dayOfMonth);
        Session session = openSession();
        Transaction tx = null;
        List<CyclicTransferDto> cyclicTransfers = null;
        
        try {
            tx = session.beginTransaction();
            cyclicTransfers = session.createQuery("from CyclicTransferDto where dayOfMonth = :dayOfMonth")
                    .setShort("dayOfMonth", (short) dayOfMonth).list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error(
                    "getAllCyclicTransfersFromDay|Wystąpił wyjątek podczas próby pobrania przelewów cyklicznych z dnia: {}",
                    dayOfMonth, e);
            throw e;
        }
        finally {
            session.close();
        }
        
        return null != cyclicTransfers ? cyclicTransfers : Collections.emptyList();
    }
}
