package systemy.bankowe.dao.card;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.card.Card;
import systemy.bankowe.dto.card.CardOperation;
import systemy.bankowe.dto.card.ChargeCard;
import systemy.bankowe.dto.card.DebitCard;

@Repository
public class CardDao extends HibernateUtil implements ICardDao, Serializable {

	private static final long serialVersionUID = -5560868682784338911L;

	
    private static final String GET_LAST_CARD_NUMBER = "from Card c where c.number is not null order by substring(c.number, 6) DESC";
	private static final String GET_DEBIT_CARD_OFFER = "from DebitCard c where c.enabled = 'Y' and c.number is null";
	private static final String GET_CHARGE_CARD_OFFER = "from ChargeCard c where c.enabled = 'Y' and c.number is null";
	private static final String GET_DEBIT_CARD_BY_ID = "from DebitCard c where c.enabled = 'Y' and c.id = :id";
	private static final String GET_CARD_BY_ID = "from Card c where c.enabled = 'Y' and c.id = :id";
	private static final String GET_CHARGE_CARD_BY_ID = "from ChargeCard c where c.enabled = 'Y' and c.id = :id";
    private static final String GET_USERS_DEBIT_CARDS = "from DebitCard c where c.enabled = 'Y' and OWNER_ID_KLIENT = :user";
    private static final String GET_USERS_CHARGE_CARDS = "from ChargeCard c where c.enabled = 'Y' and OWNER_ID_KLIENT = :user";
    private static final String GET_CARD_BY_NUMBER = "from Card c where c.enabled = 'Y' and c.number = :number";
    private static final String GET_CARD_OPERATION = "from CardOperation op where op.cardDiscriminator = :discriminator and op.cardOperationType = :operationType";
    
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
		return queryDatShit(GET_DEBIT_CARD_OFFER);
	}
	
	public List<ChargeCard> getChargeCardOffer() {
		return queryDatShit(GET_CHARGE_CARD_OFFER);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Card findCardByNumber(String number) {
        Session session = openSession();
        Transaction tx = null;
        List<Card> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_CARD_BY_NUMBER).setParameter("number", number).setMaxResults(1).list();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChargeCard> getUserChargeCards(UserDto user) {
        Session session = openSession();
        Transaction tx = null;
        List<ChargeCard> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_USERS_CHARGE_CARDS).setParameter("user", user).list();
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
	
	

	@SuppressWarnings("unchecked")
	@Override
	public ChargeCard findChargeCardById(int id) {
        Session session = openSession();
        Transaction tx = null;
        List<ChargeCard> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_CHARGE_CARD_BY_ID).setParameter("id", id).setMaxResults(1).list();
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
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Card findCardById(int id) {
        Session session = openSession();
        Transaction tx = null;
        List<Card> cards = null;
        
        try {
            tx = session.beginTransaction();
            cards = session.createQuery(GET_CARD_BY_ID).setParameter("id", id).setMaxResults(1).list();
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

	@Override
	@SuppressWarnings("unchecked")
	public CardOperation getCardOperation(String discriminator,
			String operationType) {
		
        Session session = openSession();
        Transaction tx = null;
        List<CardOperation> cardOperations = null;
        
        try {
            tx = session.beginTransaction();
            cardOperations = session.createQuery(GET_CARD_OPERATION)
            		.setParameter("discriminator", discriminator)
            		.setParameter("operationType", operationType)
            		.setMaxResults(1).list();
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
        
        if (cardOperations.isEmpty()) {
            return null;
        }
        else {
            return cardOperations.get(0);
        }
	}
	
	

}
