package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OutcomingTransfer extends AbstractTransfer implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "nr_rachunku_docelowego")
	protected String targetAccountNumber;

	@Column(name = "nazwa_odbiorcy")
	protected String targetName;

	public OutcomingTransfer() {

	}

	public OutcomingTransfer(String title, String address, double amount,
			TransferType transferType, Date realizationDate,
			String targetAccountNumber, String targetName) {
		super(title, address, amount, transferType, realizationDate);
		this.targetAccountNumber = targetAccountNumber;
		this.targetName = targetName;
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

}
