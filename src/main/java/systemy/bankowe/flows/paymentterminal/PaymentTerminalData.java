package systemy.bankowe.flows.paymentterminal;

import java.io.Serializable;

public class PaymentTerminalData implements Serializable{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1360466329900677701L;
	
	private String pin;
	private String cardNumber;
	private String accountNumber;
	private String operationType;
	private Double amount;
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
