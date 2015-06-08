package systemy.bankowe.services.card;

import java.util.List;

import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.flows.card.CardData;

public interface ICardService {
	
	public boolean addDebitCard(CardData cd);
	public List<DebitCard> loggedInUserDebitCards();
}
