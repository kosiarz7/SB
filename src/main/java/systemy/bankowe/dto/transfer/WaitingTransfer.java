package systemy.bankowe.dto.transfer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "data_realizacji")
    private Date realizationDate = Calendar.getInstance().getTime();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_typ_przelewu")
    private TransferType transferType;
    
    private String senderAccountNumber;

    public WaitingTransfer() {

    }

    public WaitingTransfer(int id, String accountNumber, String targetName, String title, String address, double value,
            TransferType type, Date dateWaitTransfer) {
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

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public void removeSpaceFromAccountNumber()
    {
        senderAccountNumber = senderAccountNumber.replace(" ", "");
        targetAccountNumber = targetAccountNumber.replace(" ", "");
    }

	@Override
	public String toString() {
		return "WaitingTransfer [id=" + id + ", dateWaitTransfer="
				+ dateWaitTransfer + ", realizationDate=" + realizationDate
				+ ", transferType=" + transferType + ", senderAccountNumber="
				+ senderAccountNumber + ", targetAccountNumber="
				+ targetAccountNumber + ", targetName=" + targetName
				+ ", title=" + title + ", address=" + address + ", amount="
				+ amount + ", type=" + type + "]";
	}


    
    
}
