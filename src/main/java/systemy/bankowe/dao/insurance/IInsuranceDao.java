package systemy.bankowe.dao.insurance;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceDto;

public interface IInsuranceDao {

	public List<InsuranceDto> getByUser(UserDto user);
	public InsuranceDto getById(Integer id);
}
