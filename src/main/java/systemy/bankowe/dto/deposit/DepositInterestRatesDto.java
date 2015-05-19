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
@Table(name = "OPROCENTOWANIE_LOKATA")
public class DepositInterestRatesDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_OPROCLOKATA")
    private Long idOproclokata;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AKTUALNE_OPROCENTOWANIE")
    private BigDecimal aktualneOprocentowanie;
    @Column(name = "DATA_AKTUALIZACJI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAktualizacji;
    @JoinColumn(name = "ID_TYPLOKATA", referencedColumnName = "ID_TYPLOKATA")
    @ManyToOne
    private DepositTypeDto idTyplokata;

    public DepositInterestRatesDto() {
    }

    public DepositInterestRatesDto(Long idOproclokata) {
        this.idOproclokata = idOproclokata;
    }

    public Long getIdOproclokata() {
        return idOproclokata;
    }

    public void setIdOproclokata(Long idOproclokata) {
        this.idOproclokata = idOproclokata;
    }

    public BigDecimal getAktualneOprocentowanie() {
        return aktualneOprocentowanie;
    }

    public void setAktualneOprocentowanie(BigDecimal aktualneOprocentowanie) {
        this.aktualneOprocentowanie = aktualneOprocentowanie;
    }

    public Date getDataAktualizacji() {
        return dataAktualizacji;
    }

    public void setDataAktualizacji(Date dataAktualizacji) {
        this.dataAktualizacji = dataAktualizacji;
    }

    public DepositTypeDto getIdTyplokata() {
        return idTyplokata;
    }

    public void setIdTyplokata(DepositTypeDto idTyplokata) {
        this.idTyplokata = idTyplokata;
    }
}

