package systemy.bankowe.dao.transfer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OutcomingTransfer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nr_rachunku_docelowego")
	private String targetAccountNumber;

	@Column(name = "nazwa_odbiorcy")
	private String targetName;

	@Column(name = "tytul")
	private String title;

	@Column(name = "adres")
	private String address;

	@Column(name = "kwota")
	private double amount;

	@JoinColumn(name = "id_typ_przelewu")
	private TransferType type;

	// TODO: Rachunek - klient tabela

	public OutcomingTransfer() {

	}

	public OutcomingTransfer(String targetAccountNumber, String targetName,
			String title, String address, double amount, TransferType type) {
		super();
		this.targetAccountNumber = targetAccountNumber;
		this.targetName = targetName;
		this.title = title;
		this.address = address;
		this.amount = amount;
		this.type = type;
	}

	public String getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(String accountNumber) {
		this.targetAccountNumber = accountNumber;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransferType getType() {
		return type;
	}

	public void setType(TransferType type) {
		this.type = type;
	}

}
