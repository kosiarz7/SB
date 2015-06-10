package systemy.bankowe.dto.insurance;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import systemy.bankowe.dto.AbstractDto;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;

@Entity
@Table(name = "ubezpieczenie")
public class InsuranceDto  extends AbstractDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8814547566769333975L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSURANCE_SEQ")
	@SequenceGenerator(name="INSURANCE_SEQ", sequenceName="INSURANCE_SEQ", allocationSize=1)
	@Column(name = "id_ubezpieczenie")
	private int id;
	
	@Column(name = "dataPoczatku")
	private Date startDate;

	@Column(name = "dataKonca")
	private Date endDate;
	
	@Column(name = "platnoscRatalna")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean payByInstallment;
	
	@Column(name = "wartosc")
	private Double total_value;
	
	//Unidirectional 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_typUbezpieczenie")
	private InsuranceTypeDto insuranceType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_rachunekUbezpieczenia")
	private InsuranceAccountDto insuranceAccount;
	
	//Unidirectional
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_klient")
	private UserDto user;
	
	//Unidirectional
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rachunekSplaty")
	private AccountDto repaymentAccount;

	@OneToMany(mappedBy = "insurance", cascade=CascadeType.ALL)
    private Set <TransportInsuranceDto> transportInsurances;

	@OneToMany(mappedBy = "insurance", cascade=CascadeType.ALL)
	private List<InsurancePaymentDto> installments;
	
	public InsuranceDto() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isPayByInstallment() {
		return payByInstallment;
	}

	public void setPayByInstallment(boolean payByInstallment) {
		this.payByInstallment = payByInstallment;
	}

	public InsuranceAccountDto getInsuranceAccount() {
		return insuranceAccount;
	}

	public void setInsuranceAccount(InsuranceAccountDto insuranceAccount) {
		this.insuranceAccount = insuranceAccount;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public AccountDto getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(AccountDto repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public Set<TransportInsuranceDto> getTransportInsurances() {
		return transportInsurances;
	}

	public void setTrasportInsurances(Set<TransportInsuranceDto> transportInsurances) {
		this.transportInsurances = transportInsurances;
	}

	public boolean add(TransportInsuranceDto e) {
		e.setInsurance(this);
		if (transportInsurances == null) {
			transportInsurances = new HashSet<TransportInsuranceDto> ();
		}
		return transportInsurances.add(e);
		
	}

	public Double getTotal_value() {
		return total_value;
	}

	public void setTotal_value(Double total_value) {
		this.total_value = total_value;
	}

	public InsuranceTypeDto getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceTypeDto insuranceType) {
		this.insuranceType = insuranceType;
	}

	public List<InsurancePaymentDto> getInstallments() {
		return installments;
	}

	public void setInstallments(List<InsurancePaymentDto> installments) {
		this.installments = installments;
	}

	public void setTransportInsurances(
			Set<TransportInsuranceDto> transportInsurances) {
		this.transportInsurances = transportInsurances;
	}
}
