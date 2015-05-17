package systemy.bankowe.services.deposit;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import systemy.bankowe.dao.deposit.DepositDao;
import systemy.bankowe.dto.deposit.Lokaty;

@Service
@Transactional(readOnly = true)
public class DepositService implements IDepositService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired private DepositDao depositDao;
	
	@Override
	@Transactional(readOnly = false)
	public void createDeposit(Lokaty deposit) {
		depositDao.createDeposit(deposit);
	}

}
