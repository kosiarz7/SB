package systemy.bankowe.dto.credit;


import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "oprocentowanie")
public class CreditRate implements Serializable {

	private static final long serialVersionUID = 3371038070703120964L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_RATE_SEQ")
    @SequenceGenerator(name = "CREDIT_RATE_SEQ", sequenceName = "OPROCENTOWANIE_SEQ", allocationSize = 1)
    @Column(name = "id_oprocentowanie", nullable = false)
    private int id;
	
	@Column(name = "wartosc")
	private Integer wartosc;
	
	@Column(name = "data_od")
	@Temporal(TemporalType.DATE)
	private Date dataOd;
	
	@Column(name = "data_do")
	@Temporal(TemporalType.DATE)
	private Date dataDo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_kredytu")
	private CreditDto kredyt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getWartosc() {
		return wartosc;
	}

	public void setWartosc(Integer wartosc) {
		this.wartosc = wartosc;
	}

	public Date getDataOd() {
		return dataOd;
	}

	public void setDataOd(Date dataOd) {
		this.dataOd = dataOd;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public void setDataDo(Date dataDo) {
		this.dataDo = dataDo;
	}

	public CreditDto getKredyt() {
		return kredyt;
	}

	public void setKredyt(CreditDto kredyt) {
		this.kredyt = kredyt;
	}
	
	
}
