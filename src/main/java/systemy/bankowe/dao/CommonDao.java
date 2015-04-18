package systemy.bankowe.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Klasa bazowa dla DAO.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CommonDao implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = -7088860771369912098L;
    /**
     * Fabryka sesji Hibernate.
     */
    protected SessionFactory sessionFactory;
    
    /**
     * Ustawia fabrykę sesji.
     * 
     * @param sessionFactory fabryka sesji.
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Otwiera nową sesje BD.
     * 
     * @return nowa sesja BD.
     */
    protected Session openSession() {
        return sessionFactory.openSession();
    }
}
