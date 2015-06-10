package systemy.bankowe.dto.insurance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name="ubezpieczenieSplata")
public class InsurancePaymentDto extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7443054971891329213L;
	
	@Id
	@Column(name = "id_ubezpieczeniesplata")
	private int id;
	
	@Column(name = "dataSplaty")
	private Date payoutDate;
	
	@Column(name ="rata")
	private Double amount;
	
	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_ubezpieczenie")
	private InsuranceDto insurance;

	public InsurancePaymentDto() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPayoutDate() {
		return payoutDate;
	}

	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InsuranceDto getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceDto insurance) {
		this.insurance = insurance;
	}
}
