package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;
import java.util.Date;

public class DriverInsuranceHistoryStub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9212524072090037800L;

	public enum InsuranceType {
		OC("tylko OC"),
		OC_AC("OC i AC");
		
		private String label;
		
		InsuranceType(String label){
			this.label = label;
		}
		
		public String getLabel(){
			return label;
		}
	}
	
	private InsuranceType insuranceType;
	
	private Integer ocUndamagedTime;
	
	private Integer acUndamagedTime;
	
	private Boolean otherInsurances;
	
	private Date insuranceStartDate;

	public DriverInsuranceHistoryStub() {
		
	}
	
	public DriverInsuranceHistoryStub(InsuranceType insurenceType,
			Integer ocUndamagedTime, Integer acUndamagedTime,
			Boolean otherInsurances, Date insuranceStartDate) {
		this.insuranceType = insurenceType;
		this.ocUndamagedTime = ocUndamagedTime;
		this.acUndamagedTime = acUndamagedTime;
		this.otherInsurances = otherInsurances;
		this.insuranceStartDate = insuranceStartDate;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insurenceType) {
		this.insuranceType = insurenceType;
	}

	public Integer getOcUndamagedTime() {
		return ocUndamagedTime;
	}

	public void setOcUndamagedTime(Integer ocUndamagedTime) {
		this.ocUndamagedTime = ocUndamagedTime;
	}

	public Integer getAcUndamagedTime() {
		return acUndamagedTime;
	}

	public void setAcUndamagedTime(Integer acUndamagedTime) {
		this.acUndamagedTime = acUndamagedTime;
	}

	public Boolean getOtherInsurances() {
		return otherInsurances;
	}

	public void setOtherInsurances(Boolean otherInsurances) {
		this.otherInsurances = otherInsurances;
	}

	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	
	public InsuranceType[] getInsuranceTypeList() {
		return InsuranceType.values();
	}
}
