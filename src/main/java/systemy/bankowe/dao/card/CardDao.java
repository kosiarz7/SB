package systemy.bankowe.dao.card;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.card.Card;
import systemy.bankowe.dto.card.DebitCard;

@Repository
public class CardDao extends HibernateUtil implements ICardDao, Serializable {

	private static final long serialVersionUID = -5560868682784338911L;

	
    private static final String GET_LAST_CARD_NUMBER = "from Card c where c.number is not null order by substring(c.number, 6) DESC";
	private static final String GET_CARD_OFFER = "from DebitCard c where c.number is null";
	private static final String GET_DEBIT_CARD_BY_ID = "from DebitCard c where c.id = :id";
    private static final String GET_USERS_DEBIT_CARDS = "from DebitCard c where OWNER_ID_KLIENT = :user";
    
	@Override
	@SuppressWarnings("unchecked")
	public String getLastCardNumber() {
        Session session = openSession();
        Transaction tx = null;
        List<Card> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_LAST_CARD_NUMBER).setMaxResults(1).list();
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
        
        if (cards.isEmpty()) {
            return null;
        }
        else {
            return cards.get(0).getNumber();
        }
	}

	public List<DebitCard> getDebitCardOffer() {
		return queryDatShit(GET_CARD_OFFER);
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DebitCard> getUserDebitCards(UserDto user) {
        Session session = openSession();
        Transaction tx = null;
        List<DebitCard> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_USERS_DEBIT_CARDS).setParameter("user", user).list();
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
        
        if (cards.isEmpty()) {
            return null;
        }
        else {
            return cards;
        }
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> queryDatShit(String query) {
        Session session = openSession();
        Transaction tx = null;
        List<T> list = null;
        
        try {
            tx = session.beginTransaction();
            list = session.createQuery(query).list();
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
        
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DebitCard findDebitCardById(int id) {
        Session session = openSession();
        Transaction tx = null;
        List<DebitCard> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_DEBIT_CARD_BY_ID).setParameter("id", id).setMaxResults(1).list();
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
        
        if (cards.isEmpty()) {
            return null;
        }
        else {
            return cards.get(0);
        }
	}
	
	

}
