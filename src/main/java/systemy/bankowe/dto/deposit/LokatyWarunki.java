package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "LOKATY_WARUNKI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LokatyWarunki.findAll", query = "SELECT l FROM LokatyWarunki l"),
    @NamedQuery(name = "LokatyWarunki.findByIdWarunkilokata", query = "SELECT l FROM LokatyWarunki l WHERE l.idWarunkilokata = :idWarunkilokata"),
    @NamedQuery(name = "LokatyWarunki.findByKwotaMin", query = "SELECT l FROM LokatyWarunki l WHERE l.kwotaMin = :kwotaMin"),
    @NamedQuery(name = "LokatyWarunki.findByKwotaMax", query = "SELECT l FROM LokatyWarunki l WHERE l.kwotaMax = :kwotaMax"),
    @NamedQuery(name = "LokatyWarunki.findByOkresMin", query = "SELECT l FROM LokatyWarunki l WHERE l.okresMin = :okresMin"),
    @NamedQuery(name = "LokatyWarunki.findByOkresMax", query = "SELECT l FROM LokatyWarunki l WHERE l.okresMax = :okresMax")})
public class LokatyWarunki implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_WARUNKILOKATA")
    private Long idWarunkilokata;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "KWOTA_MIN")
    private BigDecimal kwotaMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KWOTA_MAX")
    private BigDecimal kwotaMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OKRES_MIN")
    private BigInteger okresMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OKRES_MAX")
    private BigInteger okresMax;
    @OneToMany(mappedBy = "idWarunkilokata")
    private Collection<Lokaty> lokatyCollection;

    public LokatyWarunki() {
    }

    public LokatyWarunki(Long idWarunkilokata) {
        this.idWarunkilokata = idWarunkilokata;
    }

    public LokatyWarunki(Long idWarunkilokata, BigDecimal kwotaMin, BigDecimal kwotaMax, BigInteger okresMin, BigInteger okresMax) {
        this.idWarunkilokata = idWarunkilokata;
        this.kwotaMin = kwotaMin;
        this.kwotaMax = kwotaMax;
        this.okresMin = okresMin;
        this.okresMax = okresMax;
    }

    public Long getIdWarunkilokata() {
        return idWarunkilokata;
    }

    public void setIdWarunkilokata(Long idWarunkilokata) {
        this.idWarunkilokata = idWarunkilokata;
    }

    public BigDecimal getKwotaMin() {
        return kwotaMin;
    }

    public void setKwotaMin(BigDecimal kwotaMin) {
        this.kwotaMin = kwotaMin;
    }

    public BigDecimal getKwotaMax() {
        return kwotaMax;
    }

    public void setKwotaMax(BigDecimal kwotaMax) {
        this.kwotaMax = kwotaMax;
    }

    public BigInteger getOkresMin() {
        return okresMin;
    }

    public void setOkresMin(BigInteger okresMin) {
        this.okresMin = okresMin;
    }

    public BigInteger getOkresMax() {
        return okresMax;
    }

    public void setOkresMax(BigInteger okresMax) {
        this.okresMax = okresMax;
    }

    @XmlTransient
    public Collection<Lokaty> getLokatyCollection() {
        return lokatyCollection;
    }

    public void setLokatyCollection(Collection<Lokaty> lokatyCollection) {
        this.lokatyCollection = lokatyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWarunkilokata != null ? idWarunkilokata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LokatyWarunki)) {
            return false;
        }
        LokatyWarunki other = (LokatyWarunki) object;
        if ((this.idWarunkilokata == null && other.idWarunkilokata != null) || (this.idWarunkilokata != null && !this.idWarunkilokata.equals(other.idWarunkilokata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gospodarka.elektroniczna.dto.sib.LokatyWarunki[ idWarunkilokata=" + idWarunkilokata + " ]";
    }
    
}
