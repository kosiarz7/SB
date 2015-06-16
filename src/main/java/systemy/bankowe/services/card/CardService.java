package systemy.bankowe.services.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.account.IAccountDao;
import systemy.bankowe.dao.card.ICardDao;
import systemy.bankowe.dao.transfer.WaitingTransferDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.card.Card;
import systemy.bankowe.dto.card.CardHistory;
import systemy.bankowe.dto.card.CardOperation;
import systemy.bankowe.dto.card.CardOperationType;
import systemy.bankowe.dto.card.ChargeCard;
import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.WaitingTransfer;
import systemy.bankowe.flows.card.CardData;
import systemy.bankowe.flows.money.transfer.MoneyTransferResult;
import systemy.bankowe.flows.paymentterminal.PaymentTerminalData;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;

@Service
public class CardService implements ICardService, Serializable {
	
	

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8018620149370973179L;
	
	private ICardDao cardDao;
	private CommonDao<Card> commonCardDao;
	private ICardNumberService cardNumberService;
	private IAccountDao accountDao;
	private SpringSecurityContextUtil springSecurityUtil;
	private WaitingTransferDao waitingTransferDao;
	private IUserService userService;

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
		newCard.setLabel(cd.getLabel());
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
		
		commonCardDao.save(newCard);
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean addChargeCard(CardData cd) {
		
		if (cd.getPin() == null || cd.getCardOfferId() == null)
			return false;
		
		ChargeCard cardOffer = cardDao.findChargeCardById(cd.getCardOfferId());
		
		if (cardOffer == null)
			return false;
		
		AccountDto accountToCharge = new AccountDto();
		accountToCharge.setId(cd.getAccountId());
		
		ChargeCard newCard = new ChargeCard();
		newCard.setLabel(cd.getLabel());
		newCard.setAccountToCharge(accountToCharge);
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
		newCard.setInterest(cardOffer.getInterest());
		newCard.setReportingDayOfTheWeek(cardOffer.getReportingDayOfTheWeek());
		String nextCardNumber = cardNumberService.nextCardNumber();
		newCard.setNumber(nextCardNumber);
		
		Optional<UserData> loggedInUser = springSecurityUtil.getLoggedInUser();
		if (loggedInUser.isPresent() == false)
			return false;
		
		String newAccountName = "RACHUNEK KARTY (" + nextCardNumber + ")";
		userService.addNextAccount(newAccountName, cardOffer.getLimit().doubleValue());
		userService.getUserAccounts(loggedInUser.get());
		
		for (AccountDto a : loggedInUser.get().getUserDto().getAccounts()) {
			if (a.getName().equals(newAccountName))
			{
				newCard.setAccount(a);
				break;
			}
		}
		
		newCard.setOwner(loggedInUser.get().getUserDto());
		commonCardDao.save(newCard);
		
		return true;
	}
	
	
	@Override
	@Transactional
	public Result pay(PaymentTerminalData data) {
		
		// spr. numeru karty
		Card card = cardDao.findCardByNumber(data.getCardNumber());
		if (card == null)
			return new Result("Niepoprawny numer karty");
		
		// czy karta zablokowana
		if (card.isLocked())
			return new Result("Karta zablokowana");
		
		// spr. pin
		if (data.getOperationType().equals(CardOperationType.PROXIMITY)==false && card.getPin().equals(data.getPin()) == false)
			return new Result("Niepoprawny kod PIN");
			//TODO: moze licznik niepoprawnego wprowadzenia kodu pin
		
		// spr. czy operacja dozwolona
		CardOperation cardOperation = cardDao.getCardOperation(card.getCardType(), data.getOperationType());
		if (cardOperation == null)
			return new Result("Niedozwolona operacja dla karty tego typu");
		
		// spr. czy konto docelowe istnieje
		AccountDto destinationAccount = accountDao.getAccountByNumber(data.getAccountNumber());
		if (destinationAccount == null)
			return new Result("Niepoprawne konto docelowe");
		
		// doliczenie kosztów operacji i odsetek
		//TODO dokonczyc
		double summaryAmount = data.getAmount();
		
		// spr. czy sa dostepne srodki
		//if (card.getAccount().getSaldo() + card.getLimit().doubleValue() - summaryAmount < 0.0)
		if (card.getAccount().getSaldo() - summaryAmount < 0.0)
			return new Result("Brak dostępnych środków");
		
		// spr. czy nie przekroczono limitow
		Date startOfDay = startOfDay();
		
		List<CardHistory> dayHistory = card.getHistory().stream()
		        .filter(p -> p.getTimestamp().after(startOfDay))
		        .map(CardHistory::new)
		        .collect(Collectors.toList());
		
		Optional<BigDecimal> dayExpensesOpt = dayHistory.stream()
			.map(f -> f.getAmmount())
			.reduce(BigDecimal::add);
		
		BigDecimal summaryAmountDec = new BigDecimal(summaryAmount, MathContext.DECIMAL64);
		BigDecimal dayExpenses = dayExpensesOpt.isPresent()? dayExpensesOpt.get() : BigDecimal.ZERO;
		BigDecimal limitMinusExpenses = card.getSummaryDialyMaxAmmount().subtract(dayExpenses).subtract(summaryAmountDec);
		
		if (limitMinusExpenses.compareTo(BigDecimal.ZERO) < 0)
			return new Result("Przekroczono dzienny limit");
			
		
		List<CardHistory> dayHistoryForOperation = dayHistory.stream()
				.filter(h -> h.getOperation().getCardOperationType().equals(data.getOperationType()))
		        .map(CardHistory::new)
		        .collect(Collectors.toList());
		
		//spr. czy nie zostala przekroczona liczba operacji zblizeniowych
		if (data.getOperationType().equals(CardOperationType.PROXIMITY)) {
			if (dayHistoryForOperation.size() >= card.getProximityDialyMaxOperations())
				return new Result("Przekroczono dozwoloną ilość operacji zbliżeniowych");
		}
		
		
		Optional<BigDecimal> dayExpensesForOperationOpt = dayHistoryForOperation.stream()
				.map(f -> f.getAmmount())
				.reduce(BigDecimal::add);

		BigDecimal dayExpensesForOperation = dayExpensesForOperationOpt
				.isPresent() ? dayExpensesForOperationOpt.get()
				: BigDecimal.ZERO;

		switch (data.getOperationType()) {
			case CardOperationType.CASH:
				limitMinusExpenses = card.getCashDialyMaxAmmount();
				break;
					
			case CardOperationType.NON_CASH:
				limitMinusExpenses = card.getNoncashDailyMaxAmmount();
				break;
	
			case CardOperationType.PROXIMITY:
				limitMinusExpenses = card.getProximityDialyMaxAmmount();
				break;
	
			default:
				throw new UnsupportedOperationException();
		}

		limitMinusExpenses = limitMinusExpenses
				.subtract(dayExpensesForOperation)
				.subtract(summaryAmountDec);

		if (limitMinusExpenses.compareTo(BigDecimal.ZERO) < 0)
			return new Result("Przekroczono dzienny limit dla wybranej operacji");
		
		
		// wykonanie przelewu
		UserDto targetUser = destinationAccount.getOwners().iterator().next();
		

		WaitingTransfer waitingTransfer = new WaitingTransfer(
				0,
				destinationAccount.getNumber(),
				targetUser.getSurname(),
				"Przelew kartą",
				targetUser.getAddress(),
				data.getAmount(),
				new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER),
				new Date());
		
		waitingTransfer.setSenderAccountNumber(card.getAccount().getNumber());
		waitingTransfer.setTransferType(new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER));
				
		int statusCode = waitingTransferDao.submit(card.getOwner().getId(), waitingTransfer);
		MoneyTransferResult transferResult = new MoneyTransferResult(MoneyTransferResult.convertErrorCode(statusCode));
		
		if (transferResult.isError())
			return new Result("Błąd w trakcie definiowania przelewu - " + transferResult.getErrorMessage());
		
		// wpis do historii		
 		CardHistory history = new CardHistory(null, new Date(), summaryAmountDec, card, cardOperation);
 		card.getHistory().add(history);
 		commonCardDao.update(card);
		
		return Result.OK;
	}
	
	
	
	@Override
	public Result payChargeCardBalance(int id) {
		
		ChargeCard card = cardDao.findChargeCardById(id);
		if (card == null)
			return new Result("Nie odnaleziono karty obciążeniowej");
		
		if (card.getBalance().compareTo(BigDecimal.ZERO) >= 0)
			return new Result("Saldo karty jest nieujemne");
		
		WaitingTransfer waitingTransfer = new WaitingTransfer(
				0,
				card.getAccount().getNumber(),
				card.getOwner().getSurname(),
				"Wyrównanie rachunku karty obciążeniowej",
				card.getOwner().getAddress(),
				BigDecimal.ZERO.subtract(card.getBalance()).doubleValue(),
				new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER),
				new Date());
		
		waitingTransfer.setSenderAccountNumber(card.getAccountToCharge().getNumber());
		waitingTransfer.setTransferType(new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER));
				
		int statusCode = waitingTransferDao.submit(card.getOwner().getId(), waitingTransfer);
		MoneyTransferResult transferResult = new MoneyTransferResult(MoneyTransferResult.convertErrorCode(statusCode));
		
		if (transferResult.isError())
			return new Result("Błąd w trakcie definiowania przelewu - " + transferResult.getErrorMessage());
		
		return Result.OK;
	}

	@Override
	public boolean lockCard(int id) {
		Card card = cardDao.findCardById(id);
		if (card == null)
			return false;
		
		card.setLocked(true);
		commonCardDao.update(card);
		return true;		
	}

	@Override
	public boolean unlockCard(int id) {
		Card card = cardDao.findCardById(id);
		if (card == null)
			return false;
		
		card.setLocked(false);
		commonCardDao.update(card);
		return true;
		
	}

	@Override
	@Transactional
	public Result deleteCard(int id) {
		Card card = cardDao.findCardById(id);
		if (card == null)
			return new Result("Nie odnaleziono karty");
		
		if (card instanceof ChargeCard) {
			ChargeCard chargeCard = (ChargeCard) card;
			if (chargeCard.getBalance().compareTo(BigDecimal.ZERO) < 0)
				return new Result("Karta obciążeniowa musi mieć nieujemne saldo!");
			
			userService.closeAccount(card.getAccount().getNumber());
		}
		
		card.setEnabled(false);
		commonCardDao.update(card);
		
		return Result.OK;
	}

	private static Date startOfDay() {

		Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	
	

	@Override
	public DebitCard getDebitCardById(int id) {
		return cardDao.findDebitCardById(id);
	}

	@Override
	public ChargeCard getChargeCardById(int id) {
		return cardDao.findChargeCardById(id);
	}

	@Override
	public List<DebitCard> loggedInUserDebitCards() {
		
		Optional<UserData> loggedInUser = springSecurityUtil.getLoggedInUser();
		return cardDao.getUserDebitCards(loggedInUser.get().getUserDto());
	}

	@Override
	public List<ChargeCard> loggedInUserChargeCards() {
		
		Optional<UserData> loggedInUser = springSecurityUtil.getLoggedInUser();
		return cardDao.getUserChargeCards(loggedInUser.get().getUserDto());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setCardDao(ICardDao cardDao) {
		this.cardDao = cardDao;
	}


	public void setCommonCardDao(CommonDao<Card> commonCardDao) {
		this.commonCardDao = commonCardDao;
	}


	public void setCardNumberService(ICardNumberService cardNumberService) {
		this.cardNumberService = cardNumberService;
	}


	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setWaitingTransferDao(WaitingTransferDao waitingTransferDao) {
		this.waitingTransferDao = waitingTransferDao;
	}

	public void setSpringSecurityUtil(SpringSecurityContextUtil springSecurityUtil) {
		this.springSecurityUtil = springSecurityUtil;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private String generateSecureCode(){
		
		return String.format("%03d",(int)(Math.random()*1000));
	}

}
