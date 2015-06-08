package systemy.bankowe.flows.deposit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.deposit.DepositConditionsDto;
import systemy.bankowe.dto.deposit.DepositDto;
import systemy.bankowe.dto.deposit.DepositTypeDto;
import systemy.bankowe.services.deposit.DepositService;
import systemy.bankowe.services.user.UserService;

public class DepositBean implements Serializable{
	
		@Autowired private DepositService depositService;
		@Autowired private UserService userService;
		
		private CommonDao<DepositTypeDto> depositTypeDao;
		private static final long serialVersionUID = 1L;

		public void submit(String name){
			System.out.println(name);
		}
		
		public DepositDto getSelectedDeposit(int id){
			Set<DepositDto> deposits = depositService.getListOfDepositsForLoggedUser();
			List<DepositDto> depositsList = new ArrayList<>();
			depositsList.addAll(deposits);
			for(DepositDto deposit: depositsList){
				if(deposit.getIdLokata() == id){
					return deposit;
				}
			}
			return null;
		}
		
		public DepositTypeDto getDepositType(int index){
			List<DepositTypeDto> depositTypes = depositTypeDao.getAll(DepositTypeDto.class);
			return depositTypes.get(index);
		}
		
		public List<DepositTypeDto> getListOfDepositTypes(){
			List<DepositTypeDto> depositTypes = depositTypeDao.getAll(DepositTypeDto.class);
			return depositTypes;
		}
		
		public Set<DepositDto> getListOfDeposits(){
			Set<DepositDto> deposits = depositService.getListOfDepositsForLoggedUser();
			System.out.println(deposits.size());
			return deposits;
		}
		
		public boolean createDeposit(DepositDto deposit,DepositTypeDto depositType, AccountDto sourceAccount, AccountDto destinationAccount){
	     	
			deposit.setAccountDto(destinationAccount);
			deposit.setOkresTrwania(depositType.getDepositConditionsDto().getOkresMin());
			
			Calendar cal = Calendar.getInstance();
			deposit.setDataZalozenia(cal.getTime());
			cal.add(Calendar.DATE,deposit.getOkresTrwania());
			deposit.setDataZakonczenia(cal.getTime());
			deposit.setWartoscObecna(deposit.getWartoscStartowa());
			deposit.setIdTyplokata(depositType);
			deposit.setStatusLokaty("otwarta");

			userService.addNextDeposit(deposit,sourceAccount);
			return true;
			
		}
		
		 public boolean validateNewDepositData(final DepositDto deposit,final DepositTypeDto depositType,int accountSourceId,int accountDestinationId) {
	        
			boolean result = true;
	        List<AccountDto> accountsList = getListOfAccounts();
	        AccountDto sourceAccount = new AccountDto();
	        AccountDto destinationAccount = new AccountDto();
	        
	        for(AccountDto account:accountsList){
	        	if(account.getId() == accountSourceId ){
	        		sourceAccount = account;
	        	} 
	        	if (account.getId() == accountDestinationId){
	        		destinationAccount = account;
	        	}
	        }
	        
	        if (deposit.getWartoscStartowa() > sourceAccount.getSaldo()) {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brak wystarczaj¹cych œrodków na wskazanym koncie", "Brak wystarczaj¹cych œrodków na wskazanym koncie"));
	            result = false;
	        }
	        
	        if (deposit.getWartoscStartowa() > depositType.getDepositConditionsDto().getKwotaMax()) {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podana kwota jest wiêksza ni¿ ustalone maksimum dla tego typu lokaty", "Podana kwota jest wiêksza ni¿ ustalone maksimum dla tego typu lokaty"));
	            result = false;
	        }
	        if (deposit.getWartoscStartowa() < depositType.getDepositConditionsDto().getKwotaMin()) {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podana kwota jest ni¿sza ni¿ ustalone minimum dla tego typu lokaty",
	                    		"Podana kwota jest ni¿sza ni¿ ustalone minimum dla tego typu lokaty"));
	            result = false;
	        }
	        if(result != false)
	        	result = createDeposit(deposit, depositType,sourceAccount,destinationAccount);
	        
	        return result;
	    }
		 
		 public void closeDeposit(int id){
			 Set<DepositDto> deposits = depositService.getListOfDepositsForLoggedUser();
				List<DepositDto> depositsList = new ArrayList<>();
				depositsList.addAll(deposits);
				for(DepositDto deposit: depositsList){
					if(deposit.getIdLokata() == id){
						deposit.setStatusLokaty("nieaktywna");
						deposit.setDataZakonczenia(new Date());
						deposit.setWartoscObecna(deposit.getWartoscStartowa());
						userService.closeDeposit(deposit);		
					}
				}
		 }
		 
		 public DepositDto countDepositValue(DepositDto deposit){
			double endValue = 0;
			List<DepositTypeDto> typeList = getListOfDepositTypes();
			DepositTypeDto type = new DepositTypeDto();
			for(DepositTypeDto depositType: typeList){
				if(depositType.getIdTyplokata() == deposit.getIdLokata()){
					type = depositType;
					break;
				}
			}
			deposit.setOkresTrwania(type.getDepositConditionsDto().getOkresMin());
			if(type.getRodzajKapitalizacji().toString().equals("P")){
				endValue = (double)((double)(type.getDepositConditionsDto().getOkresMin()/(double)type.getOkresKapitalizacji())*(double)((double)type.getOkresKapitalizacji() /(double)type.getOkresNsp()) 
						* deposit.getWartoscStartowa() * type.getStopaProcentowa()*0.01);
				endValue += deposit.getWartoscStartowa();
			} else {
				
			}
			
			deposit.setWartoscObecna(endValue);
				
			return deposit;
		 }
		 
		
		public List<AccountDto> getListOfAccounts(){
			UserDto user = userService.getLoggedAccount();
			return user.getAccounts();
		}
		
		public void setDepositDao(CommonDao<DepositDto> depositDao) {
		}
		 
		public void setDepositTypeDao(CommonDao<DepositTypeDto> depositTypeDao) {
			this.depositTypeDao = depositTypeDao;
		}
		
		public void setDepositConditionsDao(CommonDao<DepositConditionsDto> depositConditionsDao) {
		}

}
