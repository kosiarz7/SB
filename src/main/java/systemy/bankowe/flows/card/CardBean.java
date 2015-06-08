package systemy.bankowe.flows.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import systemy.bankowe.dao.card.ICardDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.card.ICardService;
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
	
	public List<DebitCard> getDebitCards() {
		return cardService.loggedInUserDebitCards();
	}
	
	
    public List<AccountDto> getAccounts() {
        Optional<UserData> user = springSecurityUtil.getLoggedInUser();
        return user.isPresent() ? user.get().getUserDto().getAccounts() : new ArrayList<AccountDto>();
    }
    
    public boolean addDebitCard(CardData cd) {
    	return cardService.addDebitCard(cd);
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
	
	
}
