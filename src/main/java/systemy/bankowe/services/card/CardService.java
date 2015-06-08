package systemy.bankowe.services.card;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dao.card.ICardDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.flows.card.CardData;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.UserData;

@Service
public class CardService implements ICardService, Serializable {
	
	

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8018620149370973179L;
	
	@Autowired
	private ICardDao cardDao;
	
	@Autowired
	private CommonDao<DebitCard> commonDebitCardDao;
	
	@Autowired
	private ICardNumberService cardNumberService;
	
	@Autowired
	private IAccountDao accountDao;
	
	@Autowired
	private SpringSecurityContextUtil springSecurityUtil;

	@Override
	@Transactional
	public boolean addDebitCard(CardData cd) {
		
		if (cd.getPin() == null || cd.getCardOfferId() == null)
			return false;
		
		DebitCard cardOffer = cardDao.findDebitCardById(cd.getCardOfferId());
		Optional<UserData> loggedInUser = springSecurityUtil.getLoggedInUser();
		
		if (cardOffer == null || loggedInUser.isPresent() == false)
			return false;
		
		AccountDto account = new AccountDto();
		account.setId(cd.getAccountId());
		
		DebitCard newCard = new DebitCard();
		newCard.setAccount(account);
		newCard.setActivationDate(new Date());
		newCard.setExpirationDate(
				Date.from(
						LocalDateTime.now().plusYears(2)
						.atZone(ZoneId.systemDefault()).toInstant())
						);
		newCard.setCashDialyMaxAmmount(cardOffer.getCashDialyMaxAmmount());
		newCard.setLimit(cardOffer.getLimit());
		newCard.setEnabled(cardOffer.isEnabled());
		newCard.setLocked(cardOffer.isLocked());
		newCard.setNoncashDailyMaxAmmount(cardOffer.getNoncashDailyMaxAmmount());
		newCard.setPin(cd.getPin());
		newCard.setProximityDialyMaxAmmount(cardOffer.getProximityDialyMaxAmmount());
		newCard.setProximityDialyMaxOperations(cardOffer.getProximityDialyMaxOperations());
		newCard.setSummaryDialyMaxAmmount(cardOffer.getSummaryDialyMaxAmmount());
		newCard.setSecureCode(generateSecureCode());	
		newCard.setNumber(cardNumberService.nextCardNumber());
		newCard.setOwner(loggedInUser.get().getUserDto());
		
		commonDebitCardDao.save(newCard);
		
		return true;
	}
	
	
	
	
	@Override
	public List<DebitCard> loggedInUserDebitCards() {
		
		Optional<UserData> loggedInUser = springSecurityUtil.getLoggedInUser();
		return cardDao.getUserDebitCards(loggedInUser.get().getUserDto());
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setCardDao(ICardDao cardDao) {
		this.cardDao = cardDao;
	}



	public void setCommonDebitCardDao(CommonDao<DebitCard> commonDebitCardDao) {
		this.commonDebitCardDao = commonDebitCardDao;
	}



	public void setCardNumberService(ICardNumberService cardNumberService) {
		this.cardNumberService = cardNumberService;
	}



	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}



	public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
		this.springSecurityUtil = springSecurityUtil;
	}



	private String generateSecureCode(){
		
		return String.format("%03d",(int)(Math.random()*1000));
	}

}
