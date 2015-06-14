package systemy.bankowe.flows.credit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import systemy.bankowe.common.beans.SpringSecurityContextUtilBean;
import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dao.credit.ICreditDao;
import systemy.bankowe.dao.insurance.IInsuranceAccountDao;
import systemy.bankowe.dao.insurance.IInsuranceDao;
import systemy.bankowe.dao.insurance.IInsuranceTypeDao;
import systemy.bankowe.dao.insurance.ITransportInsuranceDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;
import systemy.bankowe.dto.insurance.InsuranceAccountDto;
import systemy.bankowe.dto.insurance.InsuranceDto;
import systemy.bankowe.dto.insurance.InsuranceTypeDto;
import systemy.bankowe.dto.insurance.TransportInsuranceDto;
import systemy.bankowe.flows.insuranceadd.DriverInsuranceHistoryStub.InsuranceType;
import systemy.bankowe.services.accountnumber.IAccountNumberService;


public class SimulationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899129693447271786L;
	
	SpringSecurityContextUtilBean springSecurityContextUtilBean;
	
	private IUserDao userDao;
	private ICreditDao creditDao;
	private CommonDao<CreditDto> commonCreditDao;
	
	private UserDto user;
	
	public List<AccountDto> getUserAccounts() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		return user.getAccounts();
	}
	    
	public List<CreditDto> getCredits() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		CreditAccountDto creditAccount = creditDao.getCreditAccount(user.getId());
		List<CreditDto> credits = new ArrayList<CreditDto>();
		if(creditAccount != null){
		credits = creditAccount.getKredyty();
		}
		return credits;
	}

	public SpringSecurityContextUtilBean getSpringSecurityContextUtilBean() {
		return springSecurityContextUtilBean;
	}

	public void setSpringSecurityContextUtilBean(
			SpringSecurityContextUtilBean springSecurityContextUtilBean) {
		this.springSecurityContextUtilBean = springSecurityContextUtilBean;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ICreditDao getCreditDao() {
		return creditDao;
	}

	public void setCreditDao(ICreditDao creditDao) {
		this.creditDao = creditDao;
	}

	public CommonDao<CreditDto> getCommonCreditDao() {
		return commonCreditDao;
	}

	public void setCommonCreditDao(CommonDao<CreditDto> commonCreditDao) {
		this.commonCreditDao = commonCreditDao;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
}
