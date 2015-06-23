package systemy.bankowe.dao.credit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.collect.Lists;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;
import systemy.bankowe.dto.credit.CreditInstallmentDto;
import systemy.bankowe.dto.insurance.InsuranceDto;

public class CreditDao extends HibernateUtil implements
ICreditDao, Serializable {

	private static final long serialVersionUID = 2458054746328752447L;
	
	private static final String GET_CREDIT_BY_USER = "FROM CreditDto WHERE account = :account";
	private static final String GET_CREDIT_ACCOUNT_BY_USER = "FROM CreditAccountDto ca WHERE ca.klient.id = :id";
	
	@Override
	public void grantCredit(CreditDto credit, UserDto user) {
		CreditAccountDto account = getCreditAccount(user.getId());
		if(account == null){
			account = createCreditAccount(user);
		}
		credit.setRachunek(account);
		Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(credit.getRodzajKredytu());
            session.save(credit);
            for (CreditInstallmentDto installment : credit.getRatyKredytu()) {
				installment.setKredyt(credit);
				session.save(installment);
			}
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
	public CreditAccountDto getCreditAccount(Integer id) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		CreditAccountDto account;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_CREDIT_ACCOUNT_BY_USER);
			query.setParameter("id", id);
			account = (CreditAccountDto) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (null != tx) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

		return account;
	}

	@Override
	public void payInstalment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateFine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CreditDto> getByUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditAccountDto createCreditAccount(UserDto user) {
		CreditAccountDto account = new CreditAccountDto();
		account.setKlient(user);
		Session session = openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(account);
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
		return account;
	}

}
