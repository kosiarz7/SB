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

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "upowaznienie_polecenia_zapl")
public class OrderToPay extends AbstractDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_przelew_przychodzacy")
    private int id;

    @Column(name = "nazwa")
    private String name;

    @Column(name = "nr_rachunku_upowaznionego")
    private String accountEmpowered;

    @Column(name = "od_kiedy")
    private Date fromDate = Calendar.getInstance().getTime();

    @Column(name = "do_kiedy")
    private Date toDate = Calendar.getInstance().getTime();

    @Column(name = "maksymalna_kwota")
    private double maxAmount;
    
    private String accountNumber;
    
    public OrderToPay() {
    }

    public OrderToPay(int id, String name, String accountEmpowered, Date fromDate, Date toDate, double maxAmount) {
        super();
        this.id = id;
        this.name = name;
        this.accountEmpowered = accountEmpowered;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxAmount = maxAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountEmpowered() {
        return accountEmpowered;
    }

    public void setAccountEmpowered(String accountEmpowered) {
        this.accountEmpowered = accountEmpowered;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
    public void removeSpaceFromAccountNumber()
    {
    	accountNumber = accountNumber.replace(" ", "");
    	accountEmpowered = accountEmpowered.replace(" ", "");
    }
}
