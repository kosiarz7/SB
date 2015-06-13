package systemy.bankowe.dao.credit;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;


public interface ICreditDao {
    void grantCredit();
    CreditAccountDto getCreditAccount(Integer id);
    void createCreditAccount();
    void payInstalment();
    void calculateFine();
	List<CreditDto> getByUser(UserDto user);
}
