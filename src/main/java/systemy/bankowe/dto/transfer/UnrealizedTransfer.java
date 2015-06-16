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
@Table(name = "przelew_niezrealizowany")
public class UnrealizedTransfer extends AbstractTransfer implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_niezrealizowany")
	private int id;

	@Column(name = "data_przyjecia_do_realizacji")
	private Date dateWaitTransfer;

	@Column(name = "blad")
	private int errorCode;

	public UnrealizedTransfer(){}
	
	public UnrealizedTransfer(String title, String address, double amount,
			TransferType transferType, Date realizationDate, int id,
			Date dateWaitTransfer, int errorCode) {
		super(title, address, amount, transferType, realizationDate);
		this.id = id;
		this.dateWaitTransfer = dateWaitTransfer;
		this.errorCode = errorCode;
	}

	public Date getDateWaitTransfer() {
		return dateWaitTransfer;
	}

	public void setDateWaitTransfer(Date dateWaitTransfer) {
		this.dateWaitTransfer = dateWaitTransfer;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

}
