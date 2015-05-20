package systemy.bankowe.services.deposit;

import java.util.List;
import java.util.Set;

import systemy.bankowe.dto.deposit.DepositDto;
import systemy.bankowe.dto.deposit.DepositTypeDto;

public interface IDepositService {

	public Set<DepositDto> getListOfDepositsForLoggedUser();
}