package systemy.bankowe.services.card;

import java.io.Serializable;
import org.springframework.stereotype.Service;

import systemy.bankowe.dao.card.ICardDao;
import systemy.bankowe.util.StringUtil;

@Service
public class CardNumberService implements ICardNumberService, Serializable{
	
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 3977308075984256474L;

	private ICardDao cardDao;

	public final static String FIRST_CARD_NUMBER = "5614020000000005";
	
	

	public void setCardDao(ICardDao cardDao) {
		this.cardDao = cardDao;
	}


	@Override
	public String nextCardNumber() {
		
		String cardNumber = cardDao.getLastCardNumber();
		
		if (cardNumber == null)
			return FIRST_CARD_NUMBER;
		
		Integer cardId = Integer.parseInt(cardNumber.substring(6,15));
		cardId++;
		cardId%=1000000000;
		
		cardNumber = cardNumber.substring(0, 6) + String.format("%09d", cardId) + "0";
		int[] digits = StringUtil.toIntArray(cardNumber);
		setCheckSum(digits);
		return StringUtil.toString(digits);
	}


	private void setCheckSum(int[] digits) {

		int sum = 0;
		boolean alt = true;
		for (int i = digits.length - 2; i >= 0; i--) {
			int temp = digits[i];
			if (alt) {
				temp *= 2;
				if (temp > 9) {
					temp -= 9;
				}
			}
			sum += temp;
			alt = !alt;
		}
		int modulo = sum % 10;
		if (modulo > 0) {
			modulo = 10 - modulo;
		}
		digits[digits.length - 1] = modulo;
	}
	
}
