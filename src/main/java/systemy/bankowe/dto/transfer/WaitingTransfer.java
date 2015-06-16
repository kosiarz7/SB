package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "przelew_wych_oczekujacy")
public class WaitingTransfer extends OutcomingTransfer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_przelew_oczekujacy")
	private int id;

	@Column(name = "data_przyjecia_do_realizacji")
	private Date dateWaitTransfer = Calendar.getInstance().getTime();

	private String senderAccountNumber;

	public WaitingTransfer() {

	}

	public WaitingTransfer(int id, String accountNumber, String targetName,
			String title, String address, double amunt,
			TransferType transferType, Date dateWaitTransfer) {
		super(title, address, amunt, transferType, dateWaitTransfer,
				accountNumber, targetName);

		this.id = id;
		this.dateWaitTransfer = dateWaitTransfer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateWaitTransfer() {
		return dateWaitTransfer;
	}

	public void setDateWaitTransfer(Date dateWaitTransfer) {
		this.dateWaitTransfer = dateWaitTransfer;
	}

	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public void removeSpaceFromAccountNumber() {
		senderAccountNumber = senderAccountNumber.replace(" ", "");
		targetAccountNumber = targetAccountNumber.replace(" ", "");
	}

	@Override
	public String toString() {
		return "WaitingTransfer [id=" + id + ", dateWaitTransfer="
				+ dateWaitTransfer + ", senderAccountNumber="
				+ senderAccountNumber + ", targetAccountNumber="
				+ targetAccountNumber + ", targetName=" + targetName
				+ ", title=" + title + ", address=" + address + ", amount="
				+ amount + ", transferType=" + transferType
				+ ", realizationDate=" + realizationDate + "]";
	}

	
}
