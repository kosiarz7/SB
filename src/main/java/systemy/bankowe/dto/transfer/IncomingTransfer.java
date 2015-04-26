package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "przelew_przychodzacy")
public class IncomingTransfer extends AbstractDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_przelew_przychodzacy")
	private int id;

	@Column(name = "nr_rachunku_wysylajacego")
	private String senderAccountNumber;

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

	@Column(name = "id_rachunek_docelowy")
	private String targetAccountNumber;

	@Column(name = "data_realizacji")
	private Date dateRealizeTransfer;

	public IncomingTransfer() {
	}

	public IncomingTransfer(int id, String senderAccountNumber,
			String targetName, String title, String address, double amount,
			TransferType type, String targetAccountNumber,
			Date dateRealizeTransfer) {
		super();
		this.id = id;
		this.senderAccountNumber = senderAccountNumber;
		this.targetName = targetName;
		this.title = title;
		this.address = address;
		this.amount = amount;
		this.type = type;
		this.targetAccountNumber = targetAccountNumber;
		this.dateRealizeTransfer = dateRealizeTransfer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
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

	public String getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(String targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public Date getDateRealizeTransfer() {
		return dateRealizeTransfer;
	}

	public void setDateRealizeTransfer(Date dateRealizeTransfer) {
		this.dateRealizeTransfer = dateRealizeTransfer;
	}
}
