package systemy.bankowe.dto.deposit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "lokaty")
public class Deposit extends AbstractDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lokata")
	private int id;
	
	@Column(name = "data_zalozenia")
	private Date dateCreation;
	
	@Column(name = "data_zakonczenia")
	private Date dateEnd;
	
	@Column(name = "okres_trwania")
	private int period;
	
	@Column(name = "wartosc_startowa")
	private double startValue;
	 
	@Column(name = "wartosc_obecna")
	private double presentValue;
		
	@Column(name = "status_lokaty")
	private String state;

	@JoinColumn(name = "id_typLokata")
	private DepositType type;

	@JoinColumn(name = "id_warunkiLokata ")
	private DepositType condition;

	public Deposit(int id, Date dateCreation, Date dateEnd, int period,
			double startValue, double presentValue, String state,
			DepositType type, DepositType condition) {
		super();
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateEnd = dateEnd;
		this.period = period;
		this.startValue = startValue;
		this.presentValue = presentValue;
		this.state = state;
		this.type = type;
		this.condition = condition;
	}

	public int getId() {
		return id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public int getPeriod() {
		return period;
	}

	public double getStartValue() {
		return startValue;
	}

	public double getPresentValue() {
		return presentValue;
	}

	public String getState() {
		return state;
	}

	public DepositType getType() {
		return type;
	}

	public DepositType getCondition() {
		return condition;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setStartValue(double startValue) {
		this.startValue = startValue;
	}

	public void setPresentValue(double presentValue) {
		this.presentValue = presentValue;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setType(DepositType type) {
		this.type = type;
	}

	public void setCondition(DepositType condition) {
		this.condition = condition;
	}

	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
