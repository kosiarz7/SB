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
public class DepositInterestDto implements Serializable {
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
    private DepositDto idLokata;

    public DepositInterestDto() {
    }

    public DepositInterestDto(BigDecimal idLokataodsetki) {
        this.idLokataodsetki = idLokataodsetki;
    }

    public DepositInterestDto(BigDecimal idLokataodsetki, Date dataDoliczenia) {
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

    public DepositDto getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(DepositDto idLokata) {
        this.idLokata = idLokata;
    }
    
}

