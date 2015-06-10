package systemy.bankowe.dao.card;

import java.util.List;

import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.card.Card;
import systemy.bankowe.dto.card.DebitCard;

public interface ICardDao {
	
	public String getLastCardNumber();
	public List<DebitCard> getDebitCardOffer();
	public DebitCard findDebitCardById(int id);
	public List<DebitCard> getUserDebitCards(UserDto user);
	public Card findCardByNumber(String number);
}
