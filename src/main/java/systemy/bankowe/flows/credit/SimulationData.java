package systemy.bankowe.flows.credit;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.collect.Lists;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.credit.CreditDao;
import systemy.bankowe.dao.credit.ICreditDao;
import systemy.bankowe.dao.user.IUserDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.credit.CreditAccountDto;
import systemy.bankowe.dto.credit.CreditDto;
import systemy.bankowe.dto.credit.CreditInstallmentDto;
import systemy.bankowe.dto.credit.CreditRate;
import systemy.bankowe.dto.credit.CreditTypeDto;

@ManagedBean
@ViewScoped
public class SimulationData implements Serializable {

	private static final long serialVersionUID = -8291195833834096313L;

	private CreditDto credit;
	private Double totalAmount;
	private Integer instalmentQuantity;
	private Double creditRate;
	private Boolean decreasingInstalment;
	private CreditView ratyWidok;
	private IUserDao userDao;
	private ICreditDao creditDao;
	private CommonDao<CreditDto> commonCreditDao;
	private CommonDao<CreditAccountDto> commonCreditAccountDao;
	private UserDto user;
	
	public void createSimulation(){
		CreditTypeDto type = new CreditTypeDto();
		type.setNazwa("Kredyt konsumpcyjny");
		credit.setKapitalPoczatkowy(inGrosz(totalAmount));
		credit.setKapitalSplacony(0);
		credit.setOdsetkiSplacone(0);
		credit.setOdsetkiKarne(0);
		credit.setRodzajKredytu(type);
		CreditRate cr = new CreditRate();
		cr.setDataOd(new Date());
		cr.setWartosc(creditRate.intValue());
		credit.setOprocentowanie(Lists.newArrayList(cr));
		credit.setCzasTrwania(instalmentQuantity);
		credit.setSplataNaRaz(1);
		credit.setNazwa("Kredyt gotówkowy");
		if(!decreasingInstalment){
			credit.setRatyKredytu(calculateEvenInstalments(totalAmount, instalmentQuantity, creditRate));
		}else{
			credit.setRatyKredytu(calculateDiminishingInstalments(totalAmount, instalmentQuantity, creditRate));
		}
		credit.setOdsetki(countInterest(credit.getRatyKredytu()));
		this.ratyWidok = new CreditView(credit.getRatyKredytu());
	}
	
	private Integer countInterest(List<CreditInstallmentDto> ratyKredytu) {
		Integer odsetki = 0;
		for (CreditInstallmentDto creditInstallmentDto : ratyKredytu) {
			odsetki += creditInstallmentDto.getOdsetki();
		}
		return odsetki;
	}

	public void init() {
		credit = new CreditDto();
		totalAmount = 0D;
		instalmentQuantity = 0;
		creditRate = 0D;
		decreasingInstalment = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		ctx.execute("PF('pbAjax').start();");
	}
	
	public void createCredit(){
		creditDao.grantCredit(credit, user);
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
			instalment.setDataPlanowaWplaty(getDatePlusMonths(i+1));
			odsetki = tempAmount*rateInPeriod;
			kapital = equalRate - odsetki;
			instalment.setOdsetki(inGrosz(odsetki));
			instalment.setKapital(inGrosz(equalRate) - instalment.getOdsetki());
			instalment.setOdsetkiKarne(0);
			instalments.add(instalment);
			tempAmount = tempAmount - kapital;
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
			instalment.setDataPlanowaWplaty(getDatePlusMonths(i+1));
			odsetki = tempAmount*rateInPeriod;
			kapital = diminishingRate - odsetki;
			instalment.setOdsetki(inGrosz(odsetki));
			instalment.setKapital(inGrosz(diminishingRate) - instalment.getOdsetki());
			instalment.setOdsetkiKarne(0);
			instalments.add(instalment);
			tempAmount = tempAmount - kapital;
		}
		return instalments;
	}
	
	public List<AccountDto> getUserAccounts() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName(); //get logged in username
	 
		user = userDao.loadUserByUserName(login);
		return user.getAccounts();
	}
	
	public Date getDatePlusMonths(Integer quantity){
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(cal.MONTH, quantity);
        return cal.getTime();
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
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ICreditDao getCreditDao() {
		return creditDao;
	}

	public void setCreditDao(ICreditDao creditDao) {
		this.creditDao = creditDao;
	}

	public CommonDao<CreditDto> getCommonCreditDao() {
		return commonCreditDao;
	}

	public void setCommonCreditDao(CommonDao<CreditDto> commonCreditDao) {
		this.commonCreditDao = commonCreditDao;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CommonDao<CreditAccountDto> getCommonCreditAccountDao() {
		return commonCreditAccountDao;
	}

	public void setCommonCreditAccountDao(
			CommonDao<CreditAccountDto> commonCreditAccountDao) {
		this.commonCreditAccountDao = commonCreditAccountDao;
	}
	
}
