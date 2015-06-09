package systemy.bankowe.dao.credit;

import systemy.bankowe.dto.credit.CreditAccountDto;


public interface ICreditDao {
    void grantCredit();
    CreditAccountDto getCreditAccount();
    void createCreditAccount();
    void payInstalment();
    void calculateFine();
}
