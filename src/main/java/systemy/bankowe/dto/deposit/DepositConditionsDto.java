package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "DepositConditionsDto.findAll", query = "SELECT l FROM DepositConditionsDto l"),
    @NamedQuery(name = "DepositConditionsDto.findByIdWarunkilokata", query = "SELECT l FROM DepositConditionsDto l WHERE l.idWarunkilokata = :idWarunkilokata")})
public class DepositConditionsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "ID_WARUNKILOKATA")
    private Long idWarunkilokata;

    @NotNull
    @Column(name = "KWOTA_MIN")
    private double kwotaMin;

    @NotNull
    @Column(name = "KWOTA_MAX")
    private double kwotaMax;

    @NotNull
    @Column(name = "OKRES_MIN")
    private int okresMin;

    @NotNull
    @Column(name = "OKRES_MAX")
    private int okresMax;

    @OneToMany(mappedBy = "depositConditionsDto")
    private Set<DepositTypeDto> depositTypes;
    
    public DepositConditionsDto() {
    }

    public DepositConditionsDto(Long idWarunkilokata) {
        this.idWarunkilokata = idWarunkilokata;
    }

    public DepositConditionsDto(Long idWarunkilokata, double kwotaMin, double kwotaMax, int okresMin, int okresMax) {
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

    public double getKwotaMin() {
        return kwotaMin;
    }

    public void setKwotaMin(double kwotaMin) {
        this.kwotaMin = kwotaMin;
    }

    public double getKwotaMax() {
        return kwotaMax;
    }

    public void setKwotaMax(double kwotaMax) {
        this.kwotaMax = kwotaMax;
    }

    public int getOkresMin() {
        return okresMin;
    }

    public void setOkresMin(int okresMin) {
        this.okresMin = okresMin;
    }

    public int getOkresMax() {
        return okresMax;
    }

    public void setOkresMax(int okresMax) {
        this.okresMax = okresMax;
    }

	public Set<DepositTypeDto> getDepositTypes() {
		return depositTypes;
	}

	public void setDepositTypes(Set<DepositTypeDto> depositTypes) {
		this.depositTypes = depositTypes;
	}

}
