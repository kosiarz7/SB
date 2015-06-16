package systemy.bankowe.flows.card;

import java.io.Serializable;

public class CardData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384872190342752236L;
	
	private Integer cardId;
	private Integer cardOfferId;
	private String pin;
	private String confirmedPin;
	private Integer accountId;
	private String label;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Integer getCardOfferId() {
		return cardOfferId;
	}
	public void setCardOfferId(Integer cardOfferId) {
		this.cardOfferId = cardOfferId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getConfirmedPin() {
		return confirmedPin;
	}
	public void setConfirmedPin(String confirmedPin) {
		this.confirmedPin = confirmedPin;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
