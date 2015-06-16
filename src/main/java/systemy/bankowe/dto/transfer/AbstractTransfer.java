package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import systemy.bankowe.dto.AbstractDto;

@MappedSuperclass
public abstract class AbstractTransfer extends AbstractDto implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "tytul")
	protected String title;

	@Column(name = "adres")
	protected String address;

	@Column(name = "kwota")
	protected double amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_typ_przelewu")
	protected TransferType transferType;

	@Column(name = "data_realizacji")
	protected Date realizationDate = Calendar.getInstance().getTime();

	public AbstractTransfer() {

	}

	public AbstractTransfer(String title, String address, double amount,
			TransferType transferType, Date realizationDate) {
		super();
		this.title = title;
		this.address = address;
		this.amount = amount;
		this.transferType = transferType;
		this.realizationDate = realizationDate;
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

	public TransferType getTransferType() {
		return transferType;
	}

	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}

	public Date getRealizationDate() {
		return realizationDate;
	}

	public void setRealizationDate(Date realizationDate) {
		this.realizationDate = realizationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
