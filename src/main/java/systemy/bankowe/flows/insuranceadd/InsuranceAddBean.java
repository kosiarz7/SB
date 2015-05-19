package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;

import systemy.bankowe.dao.insurance.IInsuranceTypeDao;
import systemy.bankowe.dto.insurance.InsuranceTypeDto;

public class InsuranceAddBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899129693447271786L;
	
	private IInsuranceTypeDao insuranceTypeDao;
	
	public String evaluateType(Integer id) {
		InsuranceTypeDto insuranceType = insuranceTypeDao.getById(id);
		return insuranceType.getType();
		
	}
	
	public String newInsurance() {
		return null;
	}

	public void setInsuranceTypeDao(IInsuranceTypeDao insuranceTypeDao) {
		this.insuranceTypeDao = insuranceTypeDao;
	}
	
}
