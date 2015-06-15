package systemy.bankowe.dao.card;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.card.Card;
import systemy.bankowe.dto.card.CardOperation;
import systemy.bankowe.dto.card.ChargeCard;
import systemy.bankowe.dto.card.DebitCard;

public interface ICardDao {
	
	public String getLastCardNumber();
	public List<DebitCard> getDebitCardOffer();
	public List<ChargeCard> getChargeCardOffer();
	public DebitCard findDebitCardById(int id);
	public ChargeCard findChargeCardById(int id);
	public List<DebitCard> getUserDebitCards(UserDto user);
	public List<ChargeCard> getUserChargeCards(UserDto user);
	public Card findCardByNumber(String number);
	public CardOperation getCardOperation(String discriminator, String operationType);
}
