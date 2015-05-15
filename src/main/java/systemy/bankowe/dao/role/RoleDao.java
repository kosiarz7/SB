package systemy.bankowe.dao.role;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.RoleDto;

/**
 * DAO dla uprawnień.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class RoleDao extends HibernateUtil implements IRoleDao, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 1424349921231004377L;
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
    public Optional<RoleDto> getRoleByName(String name) {
        LOGGER.debug("getRoleByName|Próba pobrania uprawnienia: {}", name);
        
        Session session = openSession();
        Transaction tx = null;
        List<RoleDto> roles = null;
        
        try {
            tx = session.beginTransaction();
            roles = session.createQuery("from RoleDto where name = :name").setString("name", name).list();
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
        
        Optional<RoleDto> role;
        if (roles.isEmpty()) {
            role = Optional.empty();
            LOGGER.debug("getRoleByName|Brak uprawnienia o nazwie: {}", name);
        }
        else {
            role = Optional.of(roles.get(0));
            LOGGER.debug("getRoleByName|Załadowano dane uprawnienia: {}", name);
        }
        
        return role;
    }
}
