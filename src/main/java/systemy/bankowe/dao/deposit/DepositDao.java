package systemy.bankowe.dao.deposit;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dto.deposit.Deposit;
import systemy.bankowe.dto.deposit.Lokaty;

@Repository
public class DepositDao extends CommonDao implements IDepositDao, Serializable {

	/**
	 * 
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void createDeposit(Lokaty deposit) {
		Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    try {
            tx = session.beginTransaction();
            session.save(deposit);
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
		//sessionFactory.getCurrentSession().save(deposit);
	}

	@Override
	public List<Deposit> getAllDepositsForUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
