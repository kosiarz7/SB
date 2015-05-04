package systemy.bankowe.dto.deposit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "lokaty_warunki")
public class DepositCondition  extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_warunkiLokata")
	private int id;

	@Column(name = "okres_min")
	private int minPeriod;

	@Column(name = "okres_max")
	private int maxPeriod;
	
	@Column(name = "kwota_min")
	private double minAmount;

	@Column(name = "kwota_max")
	private double maxAmount;

	public DepositCondition(int id, int minPeriod, int maxPeriod,
			double minAmount, double maxAmount) {
		super();
		this.id = id;
		this.minPeriod = minPeriod;
		this.maxPeriod = maxPeriod;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	public DepositCondition() {
		
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public int getMinPeriod() {
		return minPeriod;
	}

	public int getMaxPeriod() {
		return maxPeriod;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMinPeriod(int minPeriod) {
		this.minPeriod = minPeriod;
	}

	public void setMaxPeriod(int maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}




	
}
