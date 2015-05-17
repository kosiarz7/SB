package systemy.bankowe.dto.insurances;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import systemy.bankowe.dto.AbstractDto;

@Entity
@Table(name = "typUbezpieczenie")
public class InsuranceType extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1931724113148189081L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_typUbezpieczenie")
	private int id;

	@Column(name = "nazwa")
	private String name;

	@Column(name = "typ")
	private String type;
	
	@Column(name = "opis_korzysci")
	private String profit_desc;
	
	@Column(name = "opis_inne")
	private String other_desc;

	public InsuranceType() {
		
	}

	public InsuranceType(int id, String name, String type, String profit_desc,
			String other_desc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.profit_desc = profit_desc;
		this.other_desc = other_desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProfit_desc() {
		return profit_desc;
	}

	public void setProfit_desc(String profit_desc) {
		this.profit_desc = profit_desc;
	}

	public String getOther_desc() {
		return other_desc;
	}

	public void setOther_desc(String other_desc) {
		this.other_desc = other_desc;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
