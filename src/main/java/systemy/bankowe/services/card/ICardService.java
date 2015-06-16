package systemy.bankowe.services.card;

import java.util.List;

import systemy.bankowe.dto.card.ChargeCard;
import systemy.bankowe.dto.card.DebitCard;
import systemy.bankowe.flows.card.CardData;
import systemy.bankowe.flows.paymentterminal.PaymentTerminalData;

public interface ICardService {
	
	public boolean addDebitCard(CardData cd);
	public boolean addChargeCard(CardData cd);
	public DebitCard getDebitCardById(int id);
	public ChargeCard getChargeCardById(int id);
	public List<DebitCard> loggedInUserDebitCards();
	public List<ChargeCard> loggedInUserChargeCards();
	public Result pay(PaymentTerminalData data);
	public Result payChargeCardBalance(int id);
	public boolean lockCard(int id);
	public boolean unlockCard(int id);
	public Result deleteCard(int id);
}
