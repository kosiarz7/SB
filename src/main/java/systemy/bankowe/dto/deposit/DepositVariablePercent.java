package systemy.bankowe.dto.deposit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "oprocentowanie_lokata")
public class DepositVariablePercent  extends AbstractDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_oprocLokata")
	private int id;
	
	@Column(name = "procent_przyrost_miesieczny")
	private double increase;
	
	@JoinColumn(name = "id_typLokata")
	private DepositType type;
	

	public int getId() {
		return id;
	}

	public double getIncrease() {
		return increase;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIncrease(double increase) {
		this.increase = increase;
	}

	public DepositType getType() {
		return type;
	}

	public void setType(DepositType type) {
		this.type = type;
	}

	
	public DepositVariablePercent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepositVariablePercent(int id, double increase, DepositType type) {
		super();
		this.id = id;
		this.increase = increase;
		this.type = type;
	}



	
	
	

	
}
