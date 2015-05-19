package systemy.bankowe.dao.insurance;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceTypeDto;

public class InsuranceTypeDao extends HibernateUtil implements
		IInsuranceTypeDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2970102744370102481L;

	private static final String GET_INSURANCE_TYPE_BY_ID = "FROM InsuranceTypeDto WHERE id = :id";

	@Override
	@SuppressWarnings("unchecked")
	public InsuranceTypeDto getById(Integer id) {
		Session session = openSession();
		Transaction tx = null;
		Query query = null;
		InsuranceTypeDto type;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(GET_INSURANCE_TYPE_BY_ID);
			query.setParameter("id", id);
			type = (InsuranceTypeDto) query.uniqueResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (null != tx) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

		return type;
	}
}
