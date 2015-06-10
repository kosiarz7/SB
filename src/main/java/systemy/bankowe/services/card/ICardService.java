package systemy.bankowe.services.card;

import java.util.List;

import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.flows.card.CardData;
import systemy.bankowe.flows.paymentterminal.PaymentTerminalData;

public interface ICardService {
	
	public boolean addDebitCard(CardData cd);
	public List<DebitCard> loggedInUserDebitCards();
	public Result pay(PaymentTerminalData data);
}
