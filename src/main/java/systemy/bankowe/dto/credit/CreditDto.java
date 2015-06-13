package systemy.bankowe.dto.credit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import systemy.bankowe.dto.UserDto;

@Entity
@Table(name = "rachunek_kredytowy")
public class CreditDto implements Serializable {

	
	private static final long serialVersionUID = 690395077169198242L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_SEQ")
    @SequenceGenerator(name = "CREDIT_SEQ", sequenceName = "KREDYT_SEQ", allocationSize = 1)
    @Column(name = "id_kredytu", nullable = false)
    private int id;
	
	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "czas_trwania")
	private Integer czasTrwania;
	
	@Column(name = "splata_na_raz")
	private Integer splataNaRaz;
	
	@Column(name = "kapital_poczatkowy")
	private Integer kapitalPoczatkowy;
	
	@Column(name = "kapital_splacony")
	private Integer kapitalSplacony;
	
	@Column(name = "odsetki")
	private Integer odsetki;
	
	@Column(name = "odsetki_splacone")
	private Integer odsetkiSplacone;
	
	@Column(name = "odsetki_karne")
	private Integer odsetkiKarne;
	
	@Column(name = "data_rozp_oferty")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRozpOferty;
	
	@Column(name = "data_zako_oferty")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataZakoOferty;
	
	@Column(name = "koszt_ubezpieczenia")
	private Integer kosztUbezpieczenia;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rodzaju")
	private CreditTypeDto rodzajKredytu;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="kredyt")
	private List<CreditRate> oprocentowanie;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="kredyt")
	private List<CreditInstallmentDto> ratyKredytu;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_rachunek")
    private CreditAccountDto rachunek;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getCzasTrwania() {
		return czasTrwania;
	}

	public void setCzasTrwania(Integer czasTrwania) {
		this.czasTrwania = czasTrwania;
	}

	public Integer getSplataNaRaz() {
		return splataNaRaz;
	}

	public void setSplataNaRaz(Integer splataNaRaz) {
		this.splataNaRaz = splataNaRaz;
	}

	public Integer getKapitalPoczatkowy() {
		return kapitalPoczatkowy;
	}

	public void setKapitalPoczatkowy(Integer kapitalPoczatkowy) {
		this.kapitalPoczatkowy = kapitalPoczatkowy;
	}

	public Integer getKapitalSplacony() {
		return kapitalSplacony;
	}

	public void setKapitalSplacony(Integer kapitalSplacony) {
		this.kapitalSplacony = kapitalSplacony;
	}

	public Integer getOdsetki() {
		return odsetki;
	}

	public void setOdsetki(Integer odsetki) {
		this.odsetki = odsetki;
	}

	public Integer getOdsetkiSplacone() {
		return odsetkiSplacone;
	}

	public void setOdsetkiSplacone(Integer odsetkiSplacone) {
		this.odsetkiSplacone = odsetkiSplacone;
	}

	public Integer getOdsetkiKarne() {
		return odsetkiKarne;
	}

	public void setOdsetkiKarne(Integer odsetkiKarne) {
		this.odsetkiKarne = odsetkiKarne;
	}

	public Date getDataRozpOferty() {
		return dataRozpOferty;
	}

	public void setDataRozpOferty(Date dataRozpOferty) {
		this.dataRozpOferty = dataRozpOferty;
	}

	public Date getDataZakoOferty() {
		return dataZakoOferty;
	}

	public void setDataZakoOferty(Date dataZakoOferty) {
		this.dataZakoOferty = dataZakoOferty;
	}

	public Integer getKosztUbezpieczenia() {
		return kosztUbezpieczenia;
	}

	public void setKosztUbezpieczenia(Integer kosztUbezpieczenia) {
		this.kosztUbezpieczenia = kosztUbezpieczenia;
	}

	public CreditTypeDto getRodzajKredytu() {
		return rodzajKredytu;
	}

	public void setRodzajKredytu(CreditTypeDto rodzajKredytu) {
		this.rodzajKredytu = rodzajKredytu;
	}
	
}
