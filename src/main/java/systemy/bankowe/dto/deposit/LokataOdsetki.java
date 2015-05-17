package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "LOKATA_ODSETKI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LokataOdsetki.findAll", query = "SELECT l FROM LokataOdsetki l"),
    @NamedQuery(name = "LokataOdsetki.findByIdLokataodsetki", query = "SELECT l FROM LokataOdsetki l WHERE l.idLokataodsetki = :idLokataodsetki"),
    @NamedQuery(name = "LokataOdsetki.findByDataDoliczenia", query = "SELECT l FROM LokataOdsetki l WHERE l.dataDoliczenia = :dataDoliczenia"),
    @NamedQuery(name = "LokataOdsetki.findByKwotaDoliczona", query = "SELECT l FROM LokataOdsetki l WHERE l.kwotaDoliczona = :kwotaDoliczona")})
public class LokataOdsetki implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOKATAODSETKI")
    private BigDecimal idLokataodsetki;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_DOLICZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDoliczenia;
    @Column(name = "KWOTA_DOLICZONA")
    private BigDecimal kwotaDoliczona;
    @JoinColumn(name = "ID_LOKATA", referencedColumnName = "ID_LOKATA")
    @ManyToOne
    private Lokaty idLokata;

    public LokataOdsetki() {
    }

    public LokataOdsetki(BigDecimal idLokataodsetki) {
        this.idLokataodsetki = idLokataodsetki;
    }

    public LokataOdsetki(BigDecimal idLokataodsetki, Date dataDoliczenia) {
        this.idLokataodsetki = idLokataodsetki;
        this.dataDoliczenia = dataDoliczenia;
    }

    public BigDecimal getIdLokataodsetki() {
        return idLokataodsetki;
    }

    public void setIdLokataodsetki(BigDecimal idLokataodsetki) {
        this.idLokataodsetki = idLokataodsetki;
    }

    public Date getDataDoliczenia() {
        return dataDoliczenia;
    }

    public void setDataDoliczenia(Date dataDoliczenia) {
        this.dataDoliczenia = dataDoliczenia;
    }

    public BigDecimal getKwotaDoliczona() {
        return kwotaDoliczona;
    }

    public void setKwotaDoliczona(BigDecimal kwotaDoliczona) {
        this.kwotaDoliczona = kwotaDoliczona;
    }

    public Lokaty getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(Lokaty idLokata) {
        this.idLokata = idLokata;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLokataodsetki != null ? idLokataodsetki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LokataOdsetki)) {
            return false;
        }
        LokataOdsetki other = (LokataOdsetki) object;
        if ((this.idLokataodsetki == null && other.idLokataodsetki != null) || (this.idLokataodsetki != null && !this.idLokataodsetki.equals(other.idLokataodsetki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gospodarka.elektroniczna.dto.sib.LokataOdsetki[ idLokataodsetki=" + idLokataodsetki + " ]";
    }
    
}

