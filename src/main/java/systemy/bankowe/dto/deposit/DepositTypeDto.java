package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "LOKATA_TYP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepositTypeDto.findAll", query = "SELECT l FROM DepositTypeDto l"),
    @NamedQuery(name = "DepositTypeDto.findByIdTyplokata", query = "SELECT l FROM DepositTypeDto l WHERE l.idTyplokata = :idTyplokata")})
public class DepositTypeDto implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "ID_TYPLOKATA")
    private Long idTyplokata;
    
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAZWA_LOKATY")
    private String nazwaLokaty;

    @NotNull
    @Column(name = "STOPA_PROCENTOWA")
    private double stopaProcentowa;

    @NotNull
    @Column(name = "OKRES_NSP")
    private short okresNsp;

    @NotNull
    @Column(name = "OKRES_KAPITALIZACJI")
    private short okresKapitalizacji;

    @NotNull
    @Column(name = "RODZAJ_KAPITALIZACJI")
    private Character rodzajKapitalizacji;
  
    @NotNull
    @Column(name = "RODZAJ_OPROCENTOWANIA")
    private Character rodzajOprocentowania;
    
    @Size(max = 4)
    @Column(name = "WALUTA")
    private String waluta;
    
    @OneToMany(mappedBy = "idTyplokata")
    private List<DepositDto> lokatyCollection;
    
    @OneToMany(mappedBy = "idTyplokata")
    private Set<DepositInterestRatesDto> oprocentowanieLokataCollection;
    
    @JoinColumn(name = "ID_WARUNKILOKATA")
    @ManyToOne
    private DepositConditionsDto depositConditionsDto;

    public DepositTypeDto() {
    }

    public DepositTypeDto(Long idTyplokata) {
        this.idTyplokata = idTyplokata;
    }

    public DepositTypeDto(Long idTyplokata, String nazwaLokaty, double stopaProcentowa, short okresNsp, short okresKapitalizacji, Character rodzajKapitalizacji, Character rodzajOprocentowania) {
        this.idTyplokata = idTyplokata;
        this.nazwaLokaty = nazwaLokaty;
        this.stopaProcentowa = stopaProcentowa;
        this.okresNsp = okresNsp;
        this.okresKapitalizacji = okresKapitalizacji;
        this.rodzajKapitalizacji = rodzajKapitalizacji;
        this.rodzajOprocentowania = rodzajOprocentowania;
    }

    public Long getIdTyplokata() {
        return idTyplokata;
    }

    public void setIdTyplokata(Long idTyplokata) {
        this.idTyplokata = idTyplokata;
    }

    public String getNazwaLokaty() {
        return nazwaLokaty;
    }

    public void setNazwaLokaty(String nazwaLokaty) {
        this.nazwaLokaty = nazwaLokaty;
    }

    public double getStopaProcentowa() {
        return stopaProcentowa;
    }

    public void setStopaProcentowa(double stopaProcentowa) {
        this.stopaProcentowa = stopaProcentowa;
    }

    public short getOkresNsp() {
        return okresNsp;
    }

    public void setOkresNsp(short okresNsp) {
        this.okresNsp = okresNsp;
    }

    public short getOkresKapitalizacji() {
        return okresKapitalizacji;
    }

    public void setOkresKapitalizacji(short okresKapitalizacji) {
        this.okresKapitalizacji = okresKapitalizacji;
    }

    public Character getRodzajKapitalizacji() {
        return rodzajKapitalizacji;
    }

    public void setRodzajKapitalizacji(Character rodzajKapitalizacji) {
        this.rodzajKapitalizacji = rodzajKapitalizacji;
    }

    public Character getRodzajOprocentowania() {
        return rodzajOprocentowania;
    }

    public void setRodzajOprocentowania(Character rodzajOprocentowania) {
        this.rodzajOprocentowania = rodzajOprocentowania;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    @XmlTransient
    public Collection<DepositDto> getLokatyCollection() {
        return lokatyCollection;
    }

    public void setLokatyCollection(List<DepositDto> lokatyCollection) {
        this.lokatyCollection = lokatyCollection;
    }

    @XmlTransient
    public Collection<DepositInterestRatesDto> getOprocentowanieLokataCollection() {
        return oprocentowanieLokataCollection;
    }

    public void setOprocentowanieLokataCollection(Set<DepositInterestRatesDto> oprocentowanieLokataCollection) {
        this.oprocentowanieLokataCollection = oprocentowanieLokataCollection;
    }

	public DepositConditionsDto getDepositConditionsDto() {
		return depositConditionsDto;
	}

	public void setDepositConditionsDto(DepositConditionsDto depositConditionsDto) {
		this.depositConditionsDto = depositConditionsDto;
	}
    
}

