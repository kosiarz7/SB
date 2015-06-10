package systemy.bankowe.dao.insurance;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceDto;
import systemy.bankowe.dto.insurance.InsuranceTypeDto;

public class InsuranceDao extends HibernateUtil implements
IInsuranceDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7522520358635407988L;

	private static final String GET_INSURANCE_BY_USER = "FROM InsuranceDto WHERE user = :user";
	private static final String GET_INSURANCE_BY_ID = "FROM InsuranceDto WHERE id = :id";
	
	@Override
	@SuppressWarnings("unchecked")
	public InsuranceDto getById(Integer id) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		InsuranceDto insurance;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_INSURANCE_BY_ID);
			query.setParameter("id", id);
			insurance = (InsuranceDto) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (null != tx) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

		return insurance;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List <InsuranceDto> getByUser(UserDto user) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		List <InsuranceDto> insurances;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_INSURANCE_BY_USER);
			query.setParameter("user", user);
			insurances = (List<InsuranceDto>) query.list();
			tx.commit();
			} catch (RuntimeException e) {
				if (null != tx) {
					tx.rollback();
				}
				throw e;
			} finally {
				session.close();
			}
			return insurances;
	}
}
