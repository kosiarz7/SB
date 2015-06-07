package systemy.bankowe.flows.insuranceadd;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class InsuranceAddCarData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8291195833834096313L;

	private Integer progress;
	private String lastStep;
	
	private CarBasicInfoStub carBasicInfo;
	private CarUsageStub carUsage;
	private DriverBasicInfoStub driverInfo;
	private DriverInsuranceHistoryStub driverHistory;
	
	private Double calculatedOC;
	private Double calculatedAC;
	private Double instalment;
	
	private String myAccount;
	private Boolean payPartType;
	
	public DriverBasicInfoStub getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(DriverBasicInfoStub driverInfo) {
		this.driverInfo = driverInfo;
	}
	public DriverInsuranceHistoryStub getDriverHistory() {
		return driverHistory;
	}
	public void setDriverHistory(DriverInsuranceHistoryStub driverHistory) {
		this.driverHistory = driverHistory;
	}
	public CarBasicInfoStub getCarBasicInfo() {
		return carBasicInfo;
	}
	public void setCarBasicInfo(CarBasicInfoStub carBasicInfo) {
		this.carBasicInfo = carBasicInfo;
	}
	public CarUsageStub getCarUsage() {
		return carUsage;
	}
	public void setCarUsageStub(CarUsageStub carUsage) {
		this.carUsage = carUsage;
	}
	
	public void init() {
		carBasicInfo = new CarBasicInfoStub();
		carUsage = new CarUsageStub();
		driverInfo = new DriverBasicInfoStub();
		driverHistory = new DriverInsuranceHistoryStub();
		calculatedOC = calculatedAC = instalment = 0.0;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		ctx.execute("PF('pbAjax').start();");
		setProgress(25);
	}
	
	public List<Integer> getYearList() {
		List<Integer> productionYears = new ArrayList<Integer>();
		
		for (int i = 1900 ; i <= Integer.parseInt(Year.now().toString()) ; i++) {
			productionYears.add(new Integer(i));
		}
		
		return productionYears;
	}
	
	public Integer getProgress() {
		return progress;
	}
	
	public void setProgress(Integer progress) {
        if(progress > 100)
            this.progress = 100;
        else
        	this.progress = progress;
	}
	
	 public String onFlowProcess(FlowEvent event) {
		 	
		 if (event.getNewStep().equals(lastStep)) {
		 		setProgress(getProgress()-25);
		 		lastStep = event.getNewStep();
		 	}
		 	else {
		 		setProgress(getProgress()+25);
		 		lastStep = event.getOldStep();
		 	}		
	            return event.getNewStep();
	    }
	
	 public Double calculateOC() {
		 Double price = new Double(0.0);
		 
		 double insurance_base_value = (carBasicInfo.getProducent().getPrice()/10.0) 
				 					+ carBasicInfo.getBodyType().getPrice() 
				 					+ carBasicInfo.getFuelType().getPrice();
		 
		 if (carBasicInfo.getLpgInstalled()) {
			 insurance_base_value += 200.0;
		 }
		 
		 int car_age =  Year.now().getValue() - Integer.parseInt(carBasicInfo.getProductionYear());
		 
		 if (car_age < 4) {
			 insurance_base_value += 200.0;
		 }
		 else if (car_age > 5 && car_age < 10) {
			 insurance_base_value += 100.0;
		 }
		 
		 insurance_base_value += carUsage.getAverageYearMillage().getPrice() + carUsage.getParkingPlace().getPrice();
		 
		 if (driverInfo.getBelow26Drivers() != 0) {
			 insurance_base_value += 200.0;
		 }
		 
		 double discount = driverHistory.getOcUndamagedTime() * 0.6 / 7;
		 
		 if (driverHistory.getOtherInsurances()) {
			 insurance_base_value *= 0.1;
		 }
		 
		 insurance_base_value -= insurance_base_value * discount;
		 
		 price = new Double(insurance_base_value);
		 
		 return Math.round( price * 100.0 ) / 100.0;
	 }
	
	 public Double calculateAC() {
		
		 Double price = new Double(0.0);
		 
		 double insurance_base_value = (carBasicInfo.getProducent().getPrice()/7.0) 
				 					+ carBasicInfo.getBodyType().getPrice() 
				 					+ carBasicInfo.getFuelType().getPrice();
		 
		 if (carBasicInfo.getLpgInstalled()) {
			 insurance_base_value += 200.0;
		 }
		 
		 int car_age =  Year.now().getValue() - Integer.parseInt(carBasicInfo.getProductionYear());
		 
		 if (car_age < 4) {
			 insurance_base_value += 200.0;
		 }
		 else if (car_age > 5 && car_age < 10) {
			 insurance_base_value += 100.0;
		 }
		 
		 insurance_base_value += carUsage.getAverageYearMillage().getPrice() + carUsage.getParkingPlace().getPrice();
		 
		 if (driverInfo.getBelow26Drivers() != 0) {
			 insurance_base_value += 200.0;
		 }
		 
		 double discount = driverHistory.getOcUndamagedTime() * 0.6 / 7;
		 
		 if (driverHistory.getOtherInsurances()) {
			 insurance_base_value *= 0.1;
		 }
		 
		 insurance_base_value -= insurance_base_value * discount;
		 
		 price = new Double(insurance_base_value);
		 
		 return Math.round( price * 100.0 ) / 100.0;
	 }
	 
	public Double getCalculatedOC() {
		return calculatedOC = calculateOC();
	}
	public void setCalculatedOC(Double calculatedOC) {
		this.calculatedOC = calculateOC();
	}
	public Double getCalculatedAC() {
		return calculatedAC = calculateAC();
	}
	public void setCalculatedAC(Double calculatedAC) {
		this.calculatedAC = calculateAC();
	}
	public Double getInstalment() {
		Double instalment = new Double(0.0);
		if (driverHistory.getInsuranceType() == DriverInsuranceHistoryStub.InsuranceType.OC_AC) {
			instalment = (calculateAC() + calculateOC())/4.0;
		}
		else {
			instalment = calculateOC()/4.0;
		}
		return instalment = Math.round( instalment * 100.0 ) / 100.0;
	}
	public void setInstalment(Double instalment) {
		if (driverHistory.getInsuranceType() == DriverInsuranceHistoryStub.InsuranceType.OC_AC) {
			this.instalment = (calculateAC() + calculateOC())/4.0;
		}
		else {
			this.instalment = calculateOC()/4.0;
			this.instalment = Math.round( this.instalment * 100.0 ) / 100.0;
		}
	}
	public String getMyAccount() {
		return myAccount;
	}
	public void setMyAccount(String myAccount) {
		this.myAccount = myAccount;
	}
	public Boolean getPayPartType() {
		return payPartType;
	}
	public void setPayPartType(Boolean payPartType) {
		this.payPartType = payPartType;
	}
	 
	 /*public static <E extends Enum<E>> List<String> getEnumStringList(Class<E> e) {
		 List<String> list = new LinkedList<String>();
		    for (Enum<E> s : e.getEnumConstants()) {
		        list.add(s.name().replaceAll("_", " "));
		    }
		    return list;
	}*/
}
