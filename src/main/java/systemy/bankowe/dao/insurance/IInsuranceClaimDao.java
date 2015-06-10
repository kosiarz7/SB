package systemy.bankowe.dao.insurance;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceClaimDto;

public interface IInsuranceClaimDao {

	public List<InsuranceClaimDto> getByUser(UserDto user);
}
