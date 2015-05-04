package systemy.bankowe.dto.deposit;

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
@Table(name = "lokata_odsetki")
public class DepositIncrements  extends AbstractDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lokataOdsetki")
	private int id;
	
	
	@Column(name = "data_doliczenia")
	private Date dateIncrement;
	
	@Column(name = "kwota_doliczona")
	private double amount;
	
	@JoinColumn(name = "id_lokata")
	private Deposit deposit;

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public Date getDateIncrement() {
		return dateIncrement;
	}

	public double getAmount() {
		return amount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDateIncrement(Date dateIncrement) {
		this.dateIncrement = dateIncrement;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	public DepositIncrements(int id, Date dateIncrement, double amount,
			Deposit deposit) {
		super();
		this.id = id;
		this.dateIncrement = dateIncrement;
		this.amount = amount;
		this.deposit = deposit;
	}

	public DepositIncrements() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	
	
	
}
