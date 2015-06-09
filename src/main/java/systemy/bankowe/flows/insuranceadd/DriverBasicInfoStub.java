package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;
import java.util.Date;

public class DriverBasicInfoStub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3058646452909734117L;

	public enum Gender {
		MALE("Mê¿czyzna"),
		FEMALE("Kobieta");
		
		private String label;
		
		Gender(String label) {
			this.label = label;
		}
		
		public String getLabel() {
			return label;
		}
	}
	
	private Gender gender;
	
	public enum MartialStatus {
		SINGLE("Kawaler/Panna"),
		MARRIED("¯onaty/Zamê¿na"),
		WIDOWED("Wdowiec/Wdowa"),
		DIVORCED("Rozwiedziony/Rozwiedziona");
		
		private String label;
		
		MartialStatus(String label) {
			this.label = label;
		}
		
		public String getLabel() {
			return label;
		}
	}
	
	private MartialStatus martialStatus;
	
	private Date birthDate;

	private Integer drivinglicenceGiven;

	private Integer below26Drivers;
	
	public DriverBasicInfoStub() {
		
	}
	
	public DriverBasicInfoStub(Gender gender, MartialStatus martialStatus,
			Date birthDate, Integer drivinglicenceGiven, Integer below26Drivers) {
		this.gender = gender;
		this.martialStatus = martialStatus;
		this.birthDate = birthDate;
		this.drivinglicenceGiven = drivinglicenceGiven;
		this.below26Drivers = below26Drivers;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MartialStatus getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(MartialStatus martialStatus) {
		this.martialStatus = martialStatus;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getDrivinglicenceGiven() {
		return drivinglicenceGiven;
	}

	public void setDrivinglicenceGiven(Integer drivinglicenceGiven) {
		this.drivinglicenceGiven = drivinglicenceGiven;
	}

	public Integer getBelow26Drivers() {
		return below26Drivers;
	}

	public void setBelow26Drivers(Integer below26Drivers) {
		this.below26Drivers = below26Drivers;
	}
	
	public Gender[] getGenderList() {
		return Gender.values();
	}
	
	public MartialStatus[] getMartialStatusList() {
		return MartialStatus.values();
	}
}

