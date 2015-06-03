package systemy.bankowe.dao.definedrecipients;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.DefinedRecipientDto;
import systemy.bankowe.dto.UserDto;

public class DefindeRecipientDao extends HibernateUtil implements IDefindeRecipientDao, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -2832147375071149449L;
    @InjectLogger
    private static final Logger LOGGER = null;
    
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DefinedRecipientDto> getDefindeRecipients(UserDto userDto) {
        LOGGER.debug("getDefindeRecipients|ENTRY|UserDto: {}", userDto);
        
        Session session = openSession();
        Transaction tx = null;
        List<DefinedRecipientDto> recipients = null;
        
        try {
            tx = session.beginTransaction();
            recipients = session.createQuery("from DefinedRecipientDto where user = :user").setEntity("user", userDto)
                    .list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error(
                    "getDefindeRecipients|Wystąpił wyjątek podczas próby pobrania zdefiniowanych odbiorców dla: {}",
                    userDto, e);
            throw e;
        }
        finally {
            session.close();
        }
        
        return null != recipients ? recipients : Collections.emptyList();
    }
}
