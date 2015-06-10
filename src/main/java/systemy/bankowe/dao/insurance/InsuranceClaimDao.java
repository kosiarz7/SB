package systemy.bankowe.dao.insurance;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceClaimDto;
import systemy.bankowe.dto.insurance.InsuranceDto;

public class InsuranceClaimDao extends HibernateUtil implements Serializable,
		IInsuranceClaimDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1553864953923207301L;

	private static final String GET_INSURANCE_CLAIM_BY_USER = "FROM InsuranceClaimDto WHERE user = :user";
	
	@Override
	@SuppressWarnings("unchecked")
	public List<InsuranceClaimDto> getByUser(UserDto user) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		List<InsuranceClaimDto> claims;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_INSURANCE_CLAIM_BY_USER);
			query.setParameter("user", user);
			claims = (List<InsuranceClaimDto>) query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (null != tx) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
		return claims;
	}
}
