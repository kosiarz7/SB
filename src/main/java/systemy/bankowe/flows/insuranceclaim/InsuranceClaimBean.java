package systemy.bankowe.flows.insuranceclaim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import systemy.bankowe.common.beans.SpringSecurityContextUtilBean;
import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.insurance.IInsuranceClaimDao;
import systemy.bankowe.dao.insurance.IInsuranceDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.insurance.InsuranceClaimDto;
import systemy.bankowe.dto.insurance.InsuranceDto;

public class InsuranceClaimBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3733924036507441915L;

	SpringSecurityContextUtilBean springSecurityContextUtilBean;

	private IInsuranceDao insuranceDao;
	private IInsuranceClaimDao insuranceClaimDao;
	private IUserDao userDao;
	private CommonDao<InsuranceClaimDto> commonDao;
	
	public InsuranceClaimBean() {
		
	}
	
	public List<InsuranceDto> getInsurances() {
		List<InsuranceDto> insurances = new ArrayList<InsuranceDto>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
		UserDto user = new UserDto();
		user = userDao.loadUserByUserName(login);
		insurances = insuranceDao.getByUser(user);
		return insurances;
	}
	
	public void addNewClaim(InsuranceClaimData insuranceClaimData) {
		Logger logger = LoggerFactory.getLogger(InsuranceClaimBean.class);
		logger.error("TEST DODANIA ROSZCZENIA");
		InsuranceClaimDto insuranceClaim = new InsuranceClaimDto();
		
		InsuranceDto insurance = new InsuranceDto();
		insurance = insuranceDao.getById(insuranceClaimData.getInsuranceId());
		insuranceClaim.setInsurance(insurance);
		insuranceClaim.setDescription(insuranceClaimData.getClaimDescription());
		insuranceClaim.setClaimDate(new Date());
		insuranceClaim.setFinishDate(new Date());
		insuranceClaim.setStatus("Zg³oszone");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	    UserDto user = new UserDto();
		user = userDao.loadUserByUserName(login);
		insuranceClaim.setUser(user);
		
		commonDao.save(insuranceClaim);
	}
	
	public List<InsuranceClaimDto> getClaims() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	    UserDto user = new UserDto();
		user = userDao.loadUserByUserName(login);
		List<InsuranceClaimDto> insuranceClaims = new ArrayList<InsuranceClaimDto>();
		insuranceClaims = insuranceClaimDao.getByUser(user);
		
		return insuranceClaims;
	}
	
	public SpringSecurityContextUtilBean getSpringSecurityContextUtilBean() {
		return springSecurityContextUtilBean;
	}

	public void setSpringSecurityContextUtilBean(
			SpringSecurityContextUtilBean springSecurityContextUtilBean) {
		this.springSecurityContextUtilBean = springSecurityContextUtilBean;
	}

	public IInsuranceDao getInsuranceDao() {
		return insuranceDao;
	}

	public void setInsuranceDao(IInsuranceDao insuranceDao) {
		this.insuranceDao = insuranceDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public CommonDao<InsuranceClaimDto> getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao<InsuranceClaimDto> commonDao) {
		this.commonDao = commonDao;
	}

	public IInsuranceClaimDao getInsuranceClaimDao() {
		return insuranceClaimDao;
	}

	public void setInsuranceClaimDao(IInsuranceClaimDao insuranceClaimDao) {
		this.insuranceClaimDao = insuranceClaimDao;
	}
}
