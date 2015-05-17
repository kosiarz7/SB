package systemy.bankowe.flows.insurancemain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dto.insurances.InsuranceTypeDto;

public class InsuranceMainBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899129693447271786L;
	
	private CommonDao<InsuranceTypeDto> insuranceTypeDao;
	
	public List<InsuranceTypeStub> getInsuranceTypes(){
		
		List<InsuranceTypeDto> insuranceTypes = insuranceTypeDao.getAll(InsuranceTypeDto.class);
		
		return convertInsuranceTypes(insuranceTypes);
	}
	
	private List<InsuranceTypeStub> convertInsuranceTypes(List <InsuranceTypeDto> insuranceTypes){
		   List<InsuranceTypeStub> insuranceTypeStubs = new ArrayList<>();
	        
	        for (InsuranceTypeDto a : insuranceTypes) {
	            insuranceTypeStubs.add(new InsuranceTypeStub(a.getId(),a.getName(),a.getType(),a.getProfit_desc(),a.getOther_desc()));
	        }
	        
	        return insuranceTypeStubs;
	}

	public void setInsuranceTypeDao(CommonDao<InsuranceTypeDto> insuranceTypeDao) {
		this.insuranceTypeDao = insuranceTypeDao;
	}
}
