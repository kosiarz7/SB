package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "LOKATY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lokaty.findAll", query = "SELECT l FROM Lokaty l"),
    @NamedQuery(name = "Lokaty.findByIdLokata", query = "SELECT l FROM Lokaty l WHERE l.idLokata = :idLokata"),
    @NamedQuery(name = "Lokaty.findByOkresTrwania", query = "SELECT l FROM Lokaty l WHERE l.okresTrwania = :okresTrwania"),
    @NamedQuery(name = "Lokaty.findByDataZalozenia", query = "SELECT l FROM Lokaty l WHERE l.dataZalozenia = :dataZalozenia"),
    @NamedQuery(name = "Lokaty.findByDataZakonczenia", query = "SELECT l FROM Lokaty l WHERE l.dataZakonczenia = :dataZakonczenia"),
    @NamedQuery(name = "Lokaty.findByWartoscStartowa", query = "SELECT l FROM Lokaty l WHERE l.wartoscStartowa = :wartoscStartowa"),
    @NamedQuery(name = "Lokaty.findByWartoscObecna", query = "SELECT l FROM Lokaty l WHERE l.wartoscObecna = :wartoscObecna"),
    @NamedQuery(name = "Lokaty.findByStatusLokaty", query = "SELECT l FROM Lokaty l WHERE l.statusLokaty = :statusLokaty")})
public class Lokaty implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOKATA")
    private BigDecimal idLokata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OKRES_TRWANIA")
    private BigInteger okresTrwania;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_ZALOZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZalozenia;
    @Column(name = "DATA_ZAKONCZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZakonczenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WARTOSC_STARTOWA")
    private BigDecimal wartoscStartowa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WARTOSC_OBECNA")
    private BigDecimal wartoscObecna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STATUS_LOKATY")
    private String statusLokaty;
    @OneToMany(mappedBy = "idLokata")
    private Collection<KlientLokaty> klientLokatyCollection;
    @JoinColumn(name = "ID_WARUNKILOKATA", referencedColumnName = "ID_WARUNKILOKATA")
    @ManyToOne
    private LokatyWarunki idWarunkilokata;
    @JoinColumn(name = "ID_TYPLOKATA", referencedColumnName = "ID_TYPLOKATA")
    @ManyToOne
    private LokataTyp idTyplokata;
    @OneToMany(mappedBy = "idLokata")
    private Collection<LokataOdsetki> lokataOdsetkiCollection;

    public Lokaty() {
    }

    public Lokaty(BigDecimal idLokata) {
        this.idLokata = idLokata;
    }

    public Lokaty(BigDecimal idLokata, BigInteger okresTrwania, Date dataZalozenia, BigDecimal wartoscStartowa, BigDecimal wartoscObecna, String statusLokaty) {
        this.idLokata = idLokata;
        this.okresTrwania = okresTrwania;
        this.dataZalozenia = dataZalozenia;
        this.wartoscStartowa = wartoscStartowa;
        this.wartoscObecna = wartoscObecna;
        this.statusLokaty = statusLokaty;
    }

    public BigDecimal getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(BigDecimal idLokata) {
        this.idLokata = idLokata;
    }

    public BigInteger getOkresTrwania() {
        return okresTrwania;
    }

    public void setOkresTrwania(BigInteger okresTrwania) {
        this.okresTrwania = okresTrwania;
    }

    public Date getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public BigDecimal getWartoscStartowa() {
        return wartoscStartowa;
    }

    public void setWartoscStartowa(BigDecimal wartoscStartowa) {
        this.wartoscStartowa = wartoscStartowa;
    }

    public BigDecimal getWartoscObecna() {
        return wartoscObecna;
    }

    public void setWartoscObecna(BigDecimal wartoscObecna) {
        this.wartoscObecna = wartoscObecna;
    }

    public String getStatusLokaty() {
        return statusLokaty;
    }

    public void setStatusLokaty(String statusLokaty) {
        this.statusLokaty = statusLokaty;
    }

    @XmlTransient
    public Collection<KlientLokaty> getKlientLokatyCollection() {
        return klientLokatyCollection;
    }

    public void setKlientLokatyCollection(Collection<KlientLokaty> klientLokatyCollection) {
        this.klientLokatyCollection = klientLokatyCollection;
    }

    public LokatyWarunki getIdWarunkilokata() {
        return idWarunkilokata;
    }

    public void setIdWarunkilokata(LokatyWarunki idWarunkilokata) {
        this.idWarunkilokata = idWarunkilokata;
    }

    public LokataTyp getIdTyplokata() {
        return idTyplokata;
    }

    public void setIdTyplokata(LokataTyp idTyplokata) {
        this.idTyplokata = idTyplokata;
    }

    @XmlTransient
    public Collection<LokataOdsetki> getLokataOdsetkiCollection() {
        return lokataOdsetkiCollection;
    }

    public void setLokataOdsetkiCollection(Collection<LokataOdsetki> lokataOdsetkiCollection) {
        this.lokataOdsetkiCollection = lokataOdsetkiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLokata != null ? idLokata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokaty)) {
            return false;
        }
        Lokaty other = (Lokaty) object;
        if ((this.idLokata == null && other.idLokata != null) || (this.idLokata != null && !this.idLokata.equals(other.idLokata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gospodarka.elektroniczna.dto.sib.Lokaty[ idLokata=" + idLokata + " ]";
    }
    
}
