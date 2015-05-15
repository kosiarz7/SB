package systemy.bankowe.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wspólne metody DAO.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CommonDao<T> extends HibernateUtil implements Serializable {
    
    /**
     * UID.
     */
    private static final long serialVersionUID = -4502952114335937874L;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    
    
    /**
     * Zapis encji.
     * 
     * @param entity zapisuje encję.
     */
    public void save(T entity) {
        LOGGER.debug("save|Próba zapisu encji: {}", entity);
        Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error("save|Wystąpił błąd podczas próby zapisania encji: {}", entity, e);
            throw e;
        }
        finally {
            session.close();
        }
        LOGGER.debug("save|Zapisu encji: {} zakończył się powodzeniem.", entity);
    }
    
    /**
     * Uaktualnia encję.
     * 
     * @param entity encja.
     */
    public void update(T entity) {
        LOGGER.debug("update|Próba aktualizacji encji: {}", entity);
        Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error("update|Wystąpił błąd podczas próby aktualizacji encji: {}", entity, e);
            throw e;
        }
        finally {
            session.close();
        }
        LOGGER.debug("update|Aktualizacja encji: {} zakończył się powodzeniem.", entity);
    }
    
    /**
     * Kasuje encję.
     * 
     * @param entity encja.
     */
    public void delete(T entity) {
        LOGGER.debug("delete|Próba usunięcia encji: {}", entity);
        Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error("delete|Wystąpił błąd podczas próby usunięcia encji: {}", entity, e);
            throw e;
        }
        finally {
            session.close();
        }
        LOGGER.debug("delete|Encja zostałą usunieta.");
    }
    
    /**
     * Zwraca wszystkie encje zadanego typu.
     * 
     * @param clazz typ żądanej encji.
     * @return lista wszystkich encji.
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll(Class<T> clazz) {
        LOGGER.debug("getAll|Próba pobrania wszystkich encji typu: {}", clazz.getName());
        Session session = openSession();
        Transaction tx = null;
        List<T> entities = null;
        
        try {
            tx = session.beginTransaction();
            entities = session.createQuery("from " + clazz.getSimpleName()).list();
            tx.commit();
        }
        catch (RuntimeException e) {
            if (null != tx) {
                tx.rollback();
            }
            LOGGER.error("getAll|Wystąpił błąd podczas próby pobrania encji typu: {}", clazz.getName(), e);
            throw e;
        }
        finally {
            session.close();
        }
        
        if (null != entities && !entities.isEmpty()) {
            LOGGER.debug("getAll|Pobrano {} encje typu: {}", entities.size(), clazz.getName());
        }
        else {
            LOGGER.debug("getAll|Brak encji typu: {}", clazz.getName());
        }
        
        return entities;
    }
}
