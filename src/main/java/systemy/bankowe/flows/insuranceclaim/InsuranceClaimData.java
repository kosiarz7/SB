package systemy.bankowe.flows.insuranceclaim;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.context.RequestContext;

import systemy.bankowe.dto.insurance.InsuranceDto;
import systemy.bankowe.flows.insuranceadd.CarBasicInfoStub;
import systemy.bankowe.flows.insuranceadd.CarUsageStub;
import systemy.bankowe.flows.insuranceadd.DriverBasicInfoStub;
import systemy.bankowe.flows.insuranceadd.DriverInsuranceHistoryStub;


public class InsuranceClaimData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4915646134615827628L;

	/**
	 * Którego ubezpieczenia dotyczy szkoda
	 */
	private Integer insuranceId;
	
	/**
	 * Data wystapienia zdarzenia
	 */
	private Date claimDate;
	
	/**
	 * Opis szkody/zdarzenia
	 */
	private String claimDescription;

	public InsuranceClaimData() {
		
	}
	
	public void init() {
		
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimDescription() {
		return claimDescription;
	}

	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
}
