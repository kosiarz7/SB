package systemy.bankowe.flows.card;

import java.io.Serializable;

public class CardData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384872190342752236L;
	
	private Integer cardOfferId;
	private String pin;
	private Integer accountId;
	private String label;
	
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
