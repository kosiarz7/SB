package systemy.bankowe.dao.deposit;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import systemy.bankowe.dto.deposit.Deposit;
import systemy.bankowe.dto.deposit.Lokaty;

@Repository
@Transactional
public interface IDepositDao {
	public void createDeposit(Lokaty deposit);
	public List<Deposit> getAllDepositsForUser(String username);	
}
