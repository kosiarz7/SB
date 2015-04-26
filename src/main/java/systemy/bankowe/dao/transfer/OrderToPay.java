package systemy.bankowe.dao.transfer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upowaznienie_polecenia_zapl")
public class OrderToPay implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_przelew_przychodzacy")
	private int id;

	@Column(name = "nr_rachunku_upowaznionego")
	private String accountEmpowered;

	private Date fromDate;

	private Date toDate;

	private double maxAmount;

	// TODO: Rachunek - klient tabela

	public OrderToPay() {
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

}
