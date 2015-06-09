package systemy.bankowe.dto.credit;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "raty_kredytu")
public class CreditInstallmentDto implements Serializable{

	private static final long serialVersionUID = -231598818867781031L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_INSTALMENT_SEQ")
    @SequenceGenerator(name = "CREDIT_INSTALMENT_SEQ", sequenceName = "RATY_KREDYTU_SEQ", allocationSize = 1)
    @Column(name = "id_raty", nullable = false)
    private int id;
	
	@Column(name = "numer_raty")
	private Integer numerRaty;
	
	@Column(name = "kapital")
	private Integer kapital;
	
	@Column(name = "odsetki")
	private Integer odsetki;
	
	@Column(name = "odsetki_karne")
	private Integer odsetkiKarne;
	
	@Column(name = "data_planowa_wplaty")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPlanowaWplaty;
	
	@Column(name = "data_wplaty_raty")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataWplatyRaty;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_kredytu")
	private CreditDto kredyt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getNumerRaty() {
		return numerRaty;
	}

	public void setNumerRaty(Integer numerRaty) {
		this.numerRaty = numerRaty;
	}

	public Integer getKapital() {
		return kapital;
	}

	public void setKapital(Integer kapital) {
		this.kapital = kapital;
	}

	public Integer getOdsetki() {
		return odsetki;
	}

	public void setOdsetki(Integer odsetki) {
		this.odsetki = odsetki;
	}

	public Integer getOdsetkiKarne() {
		return odsetkiKarne;
	}

	public void setOdsetkiKarne(Integer odsetkiKarne) {
		this.odsetkiKarne = odsetkiKarne;
	}

	public Date getDataPlanowaWplaty() {
		return dataPlanowaWplaty;
	}

	public void setDataPlanowaWplaty(Date dataPlanowaWplaty) {
		this.dataPlanowaWplaty = dataPlanowaWplaty;
	}

	public Date getDataWplatyRaty() {
		return dataWplatyRaty;
	}

	public void setDataWplatyRaty(Date dataWplatyRaty) {
		this.dataWplatyRaty = dataWplatyRaty;
	}

	public CreditDto getKredyt() {
		return kredyt;
	}

	public void setKredyt(CreditDto kredyt) {
		this.kredyt = kredyt;
	}
	
}
