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
@Table(name = "przelew_wych_oczekujacy")
public class WaitingTransfer extends OutcomingTransfer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_przelew_oczekujacy")
	private int id;

	@Column(name = "data_przyjecia_do_realizacji")
	private Date dateWaitTransfer;

	public WaitingTransfer() {

	}

	public WaitingTransfer(int id, String accountNumber, String targetName,
			String title, String address, double value, TransferType type,
			Date dateWaitTransfer) {
		super(accountNumber, targetName, title, address, value, type);
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

}
