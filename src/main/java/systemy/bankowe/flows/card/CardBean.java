package systemy.bankowe.flows.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import systemy.bankowe.dao.card.ICardDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.card.ChargeCard;
import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.card.ICardService;
import systemy.bankowe.services.card.Result;
import systemy.bankowe.services.user.UserData;

public class CardBean implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1629627051157109698L;

	private SpringSecurityContextUtil springSecurityUtil;
	
	private ICardDao cardDao;
	
	@Autowired
	ICardService cardService;
	
	public List<DebitCard> getDebitCardsOffer() {
		return cardDao.getDebitCardOffer();
	}
	
	public List<ChargeCard> getChargeCardsOffer() {
		return cardDao.getChargeCardOffer();
	}
	
	public List<DebitCard> getDebitCards() {
		return cardService.loggedInUserDebitCards();
	}
	
	public List<ChargeCard> getChargeCards() {
		return cardService.loggedInUserChargeCards();
	}
	
	public DebitCard getDebitCard(int id) {
		return cardService.getDebitCardById(id);
	}
	
	public ChargeCard getChargeCard(int id) {
		return cardService.getChargeCardById(id);
	}
	
    public List<AccountDto> getAccounts() {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        return user.isPresent() ? user.get().getUserDto().getAccounts() : new ArrayList<AccountDto>();
    }
    
    public void lockCard(int id) {
    	cardService.lockCard(id);
    }
    
    public void unlockCard(int id) {
    	cardService.unlockCard(id);
    }
    
    public boolean payChargeCardBalance(int id) {
    	Result result = cardService.payChargeCardBalance(id);
    	if (result.isFail()) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd!", result.getCause()));
    		return false;
    	}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Przelew w toku, poczkekaj na jego realizacje przed ponowieniem operacji."));
    	return true;
    }
    
    public boolean deleteCard(int id) {
    	Result result = cardService.deleteCard(id);
    	if (result.isFail()) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd!", result.getCause()));
    		return false;
    	}
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Karta usunięta pomyślnie."));
    	return true;
    }
    
    public boolean addDebitCard(CardData cd) {
    	return cardService.addDebitCard(cd);
    }
    public boolean addChargeCard(CardData cd) {
    	return cardService.addChargeCard(cd);
    }

	public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
		this.springSecurityUtil = springSecurityUtil;
	}

	public void setCardDao(ICardDao cardDao) {
		this.cardDao = cardDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    public boolean validateCardData(final CardData cd) {
        String pin = cd.getPin();
        
        if (pin== null || Pattern.matches("[0-9]{4}+", pin) == false) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd!", "Kod pin musi się składać z 4 cyfr"));
            return false;
        }
        else if (pin.equals(cd.getConfirmedPin()) == false) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd!",
                            "Podane kody pin są różne."));
            return false;
        }
        
        return true;
    }
	
}
