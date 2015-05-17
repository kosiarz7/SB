package systemy.bankowe.dto.deposit;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import systemy.bankowe.dto.UserDto;


/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "KLIENT_LOKATY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KlientLokaty.findAll", query = "SELECT k FROM KlientLokaty k"),
    @NamedQuery(name = "KlientLokaty.findByIdKlientlokaty", query = "SELECT k FROM KlientLokaty k WHERE k.idKlientlokaty = :idKlientlokaty")})
public class KlientLokaty implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KLIENTLOKATY")
    private BigDecimal idKlientlokaty;
    @JoinColumn(name = "ID_LOKATA", referencedColumnName = "ID_LOKATA")
    @ManyToOne
    private Lokaty idLokata;
    @JoinColumn(name = "ID_KLIENT", referencedColumnName = "ID_KLIENT")
    @ManyToOne
    private UserDto idKlient;

    public KlientLokaty() {
    }

    public KlientLokaty(BigDecimal idKlientlokaty) {
        this.idKlientlokaty = idKlientlokaty;
    }

    public BigDecimal getIdKlientlokaty() {
        return idKlientlokaty;
    }

    public void setIdKlientlokaty(BigDecimal idKlientlokaty) {
        this.idKlientlokaty = idKlientlokaty;
    }

    public Lokaty getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(Lokaty idLokata) {
        this.idLokata = idLokata;
    }

    public UserDto getIdKlient() {
        return idKlient;
    }

    public void setIdKlient(UserDto idKlient) {
        this.idKlient = idKlient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKlientlokaty != null ? idKlientlokaty.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KlientLokaty)) {
            return false;
        }
        KlientLokaty other = (KlientLokaty) object;
        if ((this.idKlientlokaty == null && other.idKlientlokaty != null) || (this.idKlientlokaty != null && !this.idKlientlokaty.equals(other.idKlientlokaty))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gospodarka.elektroniczna.dto.sib.KlientLokaty[ idKlientlokaty=" + idKlientlokaty + " ]";
    }
    
}
