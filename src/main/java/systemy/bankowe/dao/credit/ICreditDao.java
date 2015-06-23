package systemy.bankowe.dao.credit;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;


public interface ICreditDao {
    void grantCredit(CreditDto credit, UserDto user);
    CreditAccountDto getCreditAccount(Integer id);
    CreditAccountDto createCreditAccount(UserDto user);
    void payInstalment();
    void calculateFine();
	List<CreditDto> getByUser(UserDto user);
}
