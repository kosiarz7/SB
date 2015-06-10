package systemy.bankowe.dto.credit;


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
public class CreditRate {

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
	
	
}
