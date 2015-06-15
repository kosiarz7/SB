package systemy.bankowe.flows.credit;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import com.google.common.collect.Lists;

import systemy.bankowe.dto.credit.CreditDto;
import systemy.bankowe.dto.credit.CreditInstallmentDto;
import systemy.bankowe.dto.credit.CreditRate;

@ManagedBean
@ViewScoped
public class SimulationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8291195833834096313L;

	private CreditDto credit;
	private Double totalAmount;
	private Integer instalmentQuantity;
	private Double creditRate;
	private Boolean decreasingInstalment;
	private CreditView ratyWidok;
	
	public void createSimulation(){
		credit.setKapitalPoczatkowy(inGrosz(totalAmount));
		CreditRate cr = new CreditRate();
		cr.setDataOd(new Date());
		cr.setWartosc(creditRate.intValue());
		credit.setOprocentowanie(Lists.newArrayList(cr));
		credit.setCzasTrwania(instalmentQuantity);
		if(!decreasingInstalment){
			credit.setRatyKredytu(calculateEvenInstalments(totalAmount, instalmentQuantity, creditRate));
		}else{
			credit.setRatyKredytu(calculateDiminishingInstalments(totalAmount, instalmentQuantity, creditRate));
		}
		this.ratyWidok = new CreditView(credit.getRatyKredytu());
	}
	
	public void init() {
		credit = new CreditDto();
		totalAmount = 0D;
		instalmentQuantity = 0;
		creditRate = 0D;
		decreasingInstalment = false;
		
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		ctx.execute("PF('pbAjax').start();");
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getInstalmentQuantity() {
		return instalmentQuantity;
	}

	public void setInstalmentQuantity(Integer instalmentQuantity) {
		this.instalmentQuantity = instalmentQuantity;
	}

	public Double getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(Double creditRate) {
		this.creditRate = creditRate;
	}

	public Boolean getDecreasingInstalment() {
		return decreasingInstalment;
	}

	public void setDecreasingInstalment(Boolean decreasingInstalment) {
		this.decreasingInstalment = decreasingInstalment;
	}
	
	private Double inZloty(Integer amount){
		return new Double(amount / 100.0);
	}
	
	private Integer inGrosz(Double amount){
		return new Double(amount * 100.0).intValue();
	}
	
	private List<CreditInstallmentDto> calculateEvenInstalments(Double amount, Integer instalmentQuantity, Double creditRate){
		List<CreditInstallmentDto> instalments = new ArrayList<CreditInstallmentDto>();
		Double rateInPeriod = creditRate/12;
		Double equalRate = amount * ((rateInPeriod*Math.pow((1+rateInPeriod), instalmentQuantity))/(Math.pow((1+rateInPeriod), instalmentQuantity) -1));
		equalRate = Math.round(equalRate * 100.0) / 100.0;
		CreditInstallmentDto instalment;
		Double tempAmount = amount;
		Double odsetki, kapital = 0D;
		for(int i=0; i<instalmentQuantity;i++){
			instalment = new CreditInstallmentDto();
			instalment.setNumerRaty(i+1);
			odsetki = tempAmount*rateInPeriod;
			kapital = equalRate - odsetki;
			instalment.setOdsetki(inGrosz(odsetki));
			instalment.setKapital(inGrosz(equalRate) - instalment.getOdsetki());
			instalments.add(instalment);
			tempAmount = tempAmount - kapital;
			System.out.println(instalment);
		}
		return instalments;
	}
	
	private List<CreditInstallmentDto> calculateDiminishingInstalments(Double amount, Integer instalmentQuantity, Double creditRate) {
		List<CreditInstallmentDto> instalments = new ArrayList<CreditInstallmentDto>();
		Double rateInPeriod = creditRate/12;
		Double diminishingRate = 0.;
		CreditInstallmentDto instalment;
		Double tempAmount = amount;
		Double odsetki, kapital = 0D;
		for(int i=0; i<instalmentQuantity;i++){
			diminishingRate = (amount/instalmentQuantity)*(1+(instalmentQuantity-(i+1)+1)*rateInPeriod);
			diminishingRate = Math.round(diminishingRate * 100.0) / 100.0;
			instalment = new CreditInstallmentDto();
			instalment.setNumerRaty(i+1);
			odsetki = tempAmount*rateInPeriod;
			kapital = diminishingRate - odsetki;
			instalment.setOdsetki(inGrosz(odsetki));
			instalment.setKapital(inGrosz(diminishingRate) - instalment.getOdsetki());
			instalments.add(instalment);
			tempAmount = tempAmount - kapital;
			System.out.println(instalment);
		}
		return instalments;
	}

	public CreditDto getCredit() {
		return credit;
	}

	public void setCredit(CreditDto credit) {
		this.credit = credit;
	}

	public CreditView getRatyWidok() {
		return ratyWidok;
	}

	public void setRatyWidok(CreditView ratyWidok) {
		this.ratyWidok = ratyWidok;
	}
	
}
