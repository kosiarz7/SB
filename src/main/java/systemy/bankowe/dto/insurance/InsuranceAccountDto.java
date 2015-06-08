package systemy.bankowe.dto.insurance;

import java.io.Serializable;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "rachunekUbezpieczenia")
public class InsuranceAccountDto extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8322317618782043946L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INS_ACC_SEQ")
	@SequenceGenerator(name="INS_ACC_SEQ", sequenceName="INS_ACC_SEQ", allocationSize=1)
	@Column(name = "id_rachunekUbezpieczenia")
	private int id;
	
	@Column(name = "nazwa")
	private String name;

	@Column(name = "saldo")
	private Double balance;
	
    @Column(name = "numer")
    private String number;
    
    @OneToOne(mappedBy = "insuranceAccount", cascade=CascadeType.ALL)
    private InsuranceDto insurance;
    
    public InsuranceAccountDto () {
    	
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public InsuranceDto getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceDto insurance) {
		this.insurance = insurance;
	}
	
}

