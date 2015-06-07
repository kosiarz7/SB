package systemy.bankowe.dto.insurance;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Type;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name ="ubezpieczenieKomunikacyjne")
public class TransportInsuranceDto extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962498973283097058L;
	
	
	@Id
	@SequenceGenerator(name="TRANS_INS_SEQ", sequenceName="TRANS_INS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANS_INS_SEQ")
	@Column(name = "id_uKomunikacyjne")
	private int id;
	
	@Column(name = "czyAC")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean ifAC = false;
	
	@Column(name = "wartosc_ac")
	private Double priceAC;
	
	@Column(name = "wartosc_oc")
	private Double priceOC;
	
	@Column(name = "samochod")
	private String carInfo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_ubezpieczenie")
	private InsuranceDto insurance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIfAC() {
		return ifAC;
	}

	public void setIfAC(boolean ifAC) {
		this.ifAC = ifAC;
	}

	public Double getPriceAC() {
		return priceAC;
	}

	public void setPriceAC(Double priceAC) {
		this.priceAC = priceAC;
	}

	public Double getPriceOC() {
		return priceOC;
	}

	public void setPriceOC(Double priceOC) {
		this.priceOC = priceOC;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}

	public InsuranceDto getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceDto insurance) {
		this.insurance = insurance;
	}
}