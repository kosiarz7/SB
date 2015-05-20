package systemy.bankowe.flows.insurancemain;

import java.io.Serializable;
import java.util.List;

public class InsuranceMainData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047155640203809839L;

	private List<InsuranceTypeStub> insuranceTypes;

	public List<InsuranceTypeStub> getInsuranceTypes() {
		return insuranceTypes;
	}

	public void setInsuranceTypes(List<InsuranceTypeStub> insuranceTypes) {
		this.insuranceTypes = insuranceTypes;
	}
}
