package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "przelew_przychodzacy")
public class IncomingTransfer extends AbstractTransfer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_przelew_przychodzacy")
	private int id;

	@Column(name = "nr_rachunku_wysylajacego")
	private String senderAccountNumber;

	@Column(name = "nazwa_odbiorcy")
	private String targetName;

	//@Column(name = "id_rachunek_docelowy")
	//private String targetAccountNumber;

	public IncomingTransfer() {
	}

	public IncomingTransfer(String title, String address, double amount,
			TransferType transferType, Date realizationDate) {
		super(title, address, amount, transferType, realizationDate);
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
/*
	public String getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(String targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
*/
}
