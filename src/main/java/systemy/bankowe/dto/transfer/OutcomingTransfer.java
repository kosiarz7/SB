package systemy.bankowe.dto.transfer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

import systemy.bankowe.dto.AbstractDto;

@MappedSuperclass
public abstract class OutcomingTransfer extends AbstractDto implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nr_rachunku_docelowego")
	protected String targetAccountNumber;

	@Column(name = "nazwa_odbiorcy")
	protected String targetName;

	@Column(name = "tytul")
	protected String title;

	@Column(name = "adres")
	protected String address;

	@Column(name = "kwota")
	protected double amount;

	@JoinColumn(name = "id_typ_przelewu")
	protected TransferType type;

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
