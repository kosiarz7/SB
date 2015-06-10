package systemy.bankowe.dto.insurance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;
import systemy.bankowe.dto.UserDto;

@Entity
@Table (name = "roszczenia")
public class InsuranceClaimDto extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -810376809241574640L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSURANCECLAIM_SEQ")
	@SequenceGenerator(name="INSURANCECLAIM_SEQ", sequenceName="INSURANCECLAIM_SEQ", allocationSize=1)
	@Column(name = "id_roszczenie")
	private int id;

	@Column(name = "dataZgloszenia")
	private Date claimDate;
	
	@Column(name = "opisZgloszenia")
	private String description;
	
	@Column(name = "dataZakonczenia")
	private Date finishDate;
	
	@Column(name = "status")
	private String status;
	
	//Unidirectional
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ubezpieczenie")
	private InsuranceDto insurance;
	
	//Unidirectional
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_klient")
	private UserDto user;
	
	public InsuranceClaimDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public InsuranceDto getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceDto insurance) {
		this.insurance = insurance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
}
