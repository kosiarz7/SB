package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "LokataTyp.findAll", query = "SELECT l FROM LokataTyp l"),
    @NamedQuery(name = "LokataTyp.findByIdTyplokata", query = "SELECT l FROM LokataTyp l WHERE l.idTyplokata = :idTyplokata"),
    @NamedQuery(name = "LokataTyp.findByNazwaLokaty", query = "SELECT l FROM LokataTyp l WHERE l.nazwaLokaty = :nazwaLokaty"),
    @NamedQuery(name = "LokataTyp.findByStopaProcentowa", query = "SELECT l FROM LokataTyp l WHERE l.stopaProcentowa = :stopaProcentowa"),
    @NamedQuery(name = "LokataTyp.findByOkresNsp", query = "SELECT l FROM LokataTyp l WHERE l.okresNsp = :okresNsp"),
    @NamedQuery(name = "LokataTyp.findByOkresKapitalizacji", query = "SELECT l FROM LokataTyp l WHERE l.okresKapitalizacji = :okresKapitalizacji"),
    @NamedQuery(name = "LokataTyp.findByRodzajKapitalizacji", query = "SELECT l FROM LokataTyp l WHERE l.rodzajKapitalizacji = :rodzajKapitalizacji"),
    @NamedQuery(name = "LokataTyp.findByRodzajOprocentowania", query = "SELECT l FROM LokataTyp l WHERE l.rodzajOprocentowania = :rodzajOprocentowania"),
    @NamedQuery(name = "LokataTyp.findByWaluta", query = "SELECT l FROM LokataTyp l WHERE l.waluta = :waluta")})
public class LokataTyp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TYPLOKATA")
    private Long idTyplokata;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAZWA_LOKATY")
    private String nazwaLokaty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOPA_PROCENTOWA")
    private BigDecimal stopaProcentowa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OKRES_NSP")
    private short okresNsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OKRES_KAPITALIZACJI")
    private short okresKapitalizacji;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RODZAJ_KAPITALIZACJI")
    private Character rodzajKapitalizacji;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RODZAJ_OPROCENTOWANIA")
    private Character rodzajOprocentowania;
    @Size(max = 4)
    @Column(name = "WALUTA")
    private String waluta;
    @OneToMany(mappedBy = "idTyplokata")
    private Collection<Lokaty> lokatyCollection;
    @OneToMany(mappedBy = "idTyplokata")
    private Collection<OprocentowanieLokata> oprocentowanieLokataCollection;

    public LokataTyp() {
    }

    public LokataTyp(Long idTyplokata) {
        this.idTyplokata = idTyplokata;
    }

    public LokataTyp(Long idTyplokata, String nazwaLokaty, BigDecimal stopaProcentowa, short okresNsp, short okresKapitalizacji, Character rodzajKapitalizacji, Character rodzajOprocentowania) {
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

    public BigDecimal getStopaProcentowa() {
        return stopaProcentowa;
    }

    public void setStopaProcentowa(BigDecimal stopaProcentowa) {
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
    public Collection<Lokaty> getLokatyCollection() {
        return lokatyCollection;
    }

    public void setLokatyCollection(Collection<Lokaty> lokatyCollection) {
        this.lokatyCollection = lokatyCollection;
    }

    @XmlTransient
    public Collection<OprocentowanieLokata> getOprocentowanieLokataCollection() {
        return oprocentowanieLokataCollection;
    }

    public void setOprocentowanieLokataCollection(Collection<OprocentowanieLokata> oprocentowanieLokataCollection) {
        this.oprocentowanieLokataCollection = oprocentowanieLokataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTyplokata != null ? idTyplokata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LokataTyp)) {
            return false;
        }
        LokataTyp other = (LokataTyp) object;
        if ((this.idTyplokata == null && other.idTyplokata != null) || (this.idTyplokata != null && !this.idTyplokata.equals(other.idTyplokata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gospodarka.elektroniczna.dto.sib.LokataTyp[ idTyplokata=" + idTyplokata + " ]";
    }
    
}

