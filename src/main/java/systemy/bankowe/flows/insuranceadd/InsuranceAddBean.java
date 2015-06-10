package systemy.bankowe.flows.insuranceadd;

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
import systemy.bankowe.dao.insurance.IInsuranceAccountDao;
import systemy.bankowe.dao.insurance.IInsuranceDao;
import systemy.bankowe.dao.insurance.IInsuranceTypeDao;
import systemy.bankowe.dao.insurance.ITransportInsuranceDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceAccountDto;
import systemy.bankowe.dto.insurance.InsuranceDto;
import systemy.bankowe.dto.insurance.InsuranceTypeDto;
import systemy.bankowe.dto.insurance.TransportInsuranceDto;
import systemy.bankowe.flows.insuranceadd.DriverInsuranceHistoryStub.InsuranceType;
import systemy.bankowe.services.accountnumber.IAccountNumberService;


public class InsuranceAddBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899129693447271786L;
	
	SpringSecurityContextUtilBean springSecurityContextUtilBean;
	
	private IInsuranceTypeDao insuranceTypeDao;
	private IUserDao userDao;
	private IInsuranceDao insuranceDao;
	private ITransportInsuranceDao transportInsuranceDao;
	private IInsuranceAccountDao insuranceAccountDao;
	private IAccountNumberService accountNumberService;
	private IAccountDao accountDao;
	private CommonDao<InsuranceDto> commonInsuranceDao;
	
	private UserDto user;
	
	public String evaluateType(Integer id) {
		InsuranceTypeDto insuranceType = insuranceTypeDao.getById(id);
		return insuranceType.getType();
		
	}
	
	public Double round(Double number) {
		
		return (double)Math.round(number * 100) / 100;
		
	}

	public List<AccountDto> getUserAccounts() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		return user.getAccounts();
	}
	    
	public List<InsuranceDto> getInsurances() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		List<InsuranceDto> insurances = new ArrayList<InsuranceDto>();
		insurances = insuranceDao.getByUser(user);
		return insurances;
	}
	
	public void newInsurance(InsuranceAddCarData insuranceAddDataCar, Integer typeId) {
		TransportInsuranceDto transportInsurance = new TransportInsuranceDto();
		InsuranceAccountDto insuranceAccount = new InsuranceAccountDto();
		InsuranceDto insurance = new InsuranceDto();
		AccountDto repaymentAccount = new AccountDto();
		
		//InsuranceAccountDto
		String insuranceAccountName = new String(insuranceAddDataCar.getCarBasicInfo().getProducent().getLabel() + 
				" " + insuranceAddDataCar.getCarBasicInfo().getModel() + 
				" " + insuranceAddDataCar.getCarBasicInfo().getProductionYear());
		insuranceAccount.setName(insuranceAccountName);
		insuranceAccount.setNumber(accountNumberService.createNewAccountNumber());
		insuranceAccount.setBalance(-(insuranceAddDataCar.getCalculatedAC()+insuranceAddDataCar.getCalculatedOC()));
		insuranceAccount.setInsurance(insurance);
		
		//InsuranceDto
		repaymentAccount = accountDao.getAccountByNumber(insuranceAddDataCar.getMyAccount()); 
		insurance.setInsuranceAccount(insuranceAccount);
		insurance.setRepaymentAccount(repaymentAccount);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
		insurance.setUser(userDao.loadUserByUserName(login));
		Calendar cal = Calendar.getInstance();
		Date startDate = insuranceAddDataCar.getDriverHistory().getInsuranceStartDate();
		cal.setTime(startDate);
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		insurance.setStartDate(startDate);
		insurance.setEndDate(nextYear);
		InsuranceTypeDto insuranceType = insuranceTypeDao.getById(typeId);
		insurance.setInsuranceType(insuranceType);
		insurance.setTotal_value(insuranceAddDataCar.getCalculatedAC()+insuranceAddDataCar.getCalculatedOC());
		insurance.setPayByInstallment(insuranceAddDataCar.getPayPartType());
		insurance.add(transportInsurance);		
		
		//TransportInsuranceDto
		if (insuranceAddDataCar.getDriverHistory().getInsuranceType() == InsuranceType.OC_AC) {
			transportInsurance.setIfAC(true);
		}
		transportInsurance.setPriceAC(insuranceAddDataCar.getCalculatedAC());
		transportInsurance.setPriceOC(insuranceAddDataCar.getCalculatedOC());
		String carInfo = new String(
				"Marka:"+insuranceAddDataCar.getCarBasicInfo().getProducent().getLabel() +";"+
				"Model:"+insuranceAddDataCar.getCarBasicInfo().getModel() +";"+
				"Rok produkcji:"+insuranceAddDataCar.getCarBasicInfo().getProductionYear() +";"+
				"Nadwozie:"+insuranceAddDataCar.getCarBasicInfo().getBodyType().toString() +";"+
				"Silnik:"+insuranceAddDataCar.getCarBasicInfo().getEngine_size()+" "+insuranceAddDataCar.getCarBasicInfo().getFuelType().toString() +";"
				);
		transportInsurance.setCarInfo(carInfo);	
		
		//CascadeType.ALL should persist transportInsurance and insuranceAccount instances
		commonInsuranceDao.save(insurance);
	}

	public void setInsuranceTypeDao(IInsuranceTypeDao insuranceTypeDao) {
		this.insuranceTypeDao = insuranceTypeDao;
	}

	public IInsuranceTypeDao getInsuranceTypeDao() {
		return insuranceTypeDao;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IInsuranceDao getInsuranceDao() {
		return insuranceDao;
	}

	public void setInsuranceDao(IInsuranceDao insuranceDao) {
		this.insuranceDao = insuranceDao;
	}

	public ITransportInsuranceDao getTransportInsuranceDao() {
		return transportInsuranceDao;
	}

	public void setTransportInsuranceDao(
			ITransportInsuranceDao transportInsuranceDao) {
		this.transportInsuranceDao = transportInsuranceDao;
	}

	public IInsuranceAccountDao getInsuranceAccountDao() {
		return insuranceAccountDao;
	}

	public void setInsuranceAccountDao(IInsuranceAccountDao insuranceAccountDao) {
		this.insuranceAccountDao = insuranceAccountDao;
	}
	
    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

	public IAccountNumberService getAccountNumberService() {
		return accountNumberService;
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public CommonDao<InsuranceDto> getCommonInsuranceDao() {
		return commonInsuranceDao;
	}

	public void setCommonInsuranceDao(CommonDao<InsuranceDto> commonInsuranceDao) {
		this.commonInsuranceDao = commonInsuranceDao;
	}
}
