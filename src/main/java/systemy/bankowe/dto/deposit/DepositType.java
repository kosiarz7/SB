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
@Table(name = "lokata_typ")
public class DepositType extends AbstractDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_typLokata")
	private int id;

	@Column(name = "nazwa_lokaty")
	private String typeName;
	
	
	@Column(name = "stopa_procentowa")
	private double percent;
	
	@Column(name = "okres_NSP")
	private int periodNSP;
	
	@Column(name = "okres_kapitalizacji")
	private int periodCapitalization;

	@Column(name = "moment_kapitalizacji")
	private String moment;
	
	@Column(name = "rodzaj_kapitalizacji")
	private String typeCapitalization;
	
	@Column(name = "waluta")
	private String currency;

	public int getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public double getPercent() {
		return percent;
	}

	public int getPeriodNSP() {
		return periodNSP;
	}

	public int getPeriodCapitalization() {
		return periodCapitalization;
	}

	public String getMoment() {
		return moment;
	}

	public String getTypeCapitalization() {
		return typeCapitalization;
	}

	public String getCurrency() {
		return currency;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public void setPeriodNSP(int periodNSP) {
		this.periodNSP = periodNSP;
	}

	public void setPeriodCapitalization(int periodCapitalization) {
		this.periodCapitalization = periodCapitalization;
	}

	public void setMoment(String moment) {
		this.moment = moment;
	}

	public void setTypeCapitalization(String typeCapitalization) {
		this.typeCapitalization = typeCapitalization;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public DepositType(int id, String typeName, double percent, int periodNSP,
			int periodCapitalization, String moment, String typeCapitalization,
			String currency) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.percent = percent;
		this.periodNSP = periodNSP;
		this.periodCapitalization = periodCapitalization;
		this.moment = moment;
		this.typeCapitalization = typeCapitalization;
		this.currency = currency;
	}

	public DepositType() {
		
		// TODO Auto-generated constructor stub
	}
		
	
	 
	 
	
	 

	
	
}
