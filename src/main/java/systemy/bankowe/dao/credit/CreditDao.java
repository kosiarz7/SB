package systemy.bankowe.dao.credit;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;
import systemy.bankowe.dto.insurance.InsuranceDto;

public class CreditDao extends HibernateUtil implements
ICreditDao, Serializable {

	private static final long serialVersionUID = 2458054746328752447L;
	
	private static final String GET_CREDIT_BY_USER = "FROM CreditDto WHERE account = :account";
	private static final String GET_CREDIT_ACCOUNT_BY_USER = "FROM CreditAccountDto WHERE user = :user";
	
	@Override
	public void grantCredit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CreditAccountDto getCreditAccount(Integer id) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		CreditAccountDto credit;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_CREDIT_ACCOUNT_BY_USER);
			query.setParameter("id", id);
			credit = (CreditAccountDto) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (null != tx) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

		return credit;
	}

	@Override
	public void createCreditAccount() {
		// TODO Auto-generated method stub
		
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

}
