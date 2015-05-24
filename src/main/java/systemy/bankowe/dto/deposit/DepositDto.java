package systemy.bankowe.dto.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;

/**
 *
 * @author Dariusz
 */
@Entity
@Table(name = "LOKATY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepositDto.findAll", query = "SELECT l FROM DepositDto l"),
    @NamedQuery(name = "DepositDto.findByIdLokata", query = "SELECT l FROM DepositDto l WHERE l.idLokata = :idLokata")})
public class DepositDto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_seq")
    @SequenceGenerator(name = "deposit_seq", sequenceName = "deposit_seq",allocationSize = 1)
    @Column(name = "ID_LOKATA")
    private int idLokata;

    @NotNull
    @Column(name = "OKRES_TRWANIA")
    private int okresTrwania;

    @NotNull
    @Column(name = "DATA_ZALOZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZalozenia;
    
    @Column(name = "DATA_ZAKONCZENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataZakonczenia;

    @NotNull
    @Column(name = "WARTOSC_STARTOWA")
    private double wartoscStartowa;

    @NotNull
    @Column(name = "WARTOSC_OBECNA")
    private double wartoscObecna;
  
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STATUS_LOKATY")
    private String statusLokaty;
    
    @Column(name = "odnawialny")
    private boolean odnawialny;
    
    @ManyToOne
    @JoinColumn(name ="id_rachunekDocelowy", referencedColumnName = "id_rachunek")
    private AccountDto accountDto;

    @JoinColumn(name = "ID_TYPLOKATA", referencedColumnName = "ID_TYPLOKATA")
    @ManyToOne
    private DepositTypeDto idTyplokata;
    
    @OneToMany(mappedBy = "idLokata")
    private Set<DepositInterestDto> lokataOdsetkiCollection;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "deposits")
    private Set<UserDto> owners;

    protected DepositDto() {
    }

    public DepositDto(int idLokata) {
        this.idLokata = idLokata;
    }

    public DepositDto(int idLokata, int okresTrwania, Date dataZalozenia, double wartoscStartowa, double wartoscObecna, String statusLokaty) {
        this.idLokata = idLokata;
        this.okresTrwania = okresTrwania;
        this.dataZalozenia = dataZalozenia;
        this.wartoscStartowa = wartoscStartowa;
        this.wartoscObecna = wartoscObecna;
        this.statusLokaty = statusLokaty;
    }

    public int getIdLokata() {
        return idLokata;
    }

    public void setIdLokata(int idLokata) {
        this.idLokata = idLokata;
    }

    public int getOkresTrwania() {
        return okresTrwania;
    }

    public void setOkresTrwania(int okresTrwania) {
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

    public double getWartoscStartowa() {
        return wartoscStartowa;
    }

    public void setWartoscStartowa(double wartoscStartowa) {
        this.wartoscStartowa = wartoscStartowa;
    }

    public double getWartoscObecna() {
        return wartoscObecna;
    }

    public void setWartoscObecna(double wartoscObecna) {
        this.wartoscObecna = wartoscObecna;
    }

    public String getStatusLokaty() {
        return statusLokaty;
    }

    public void setStatusLokaty(String statusLokaty) {
        this.statusLokaty = statusLokaty;
    }

    public DepositTypeDto getIdTyplokata() {
        return idTyplokata;
    }

    public void setIdTyplokata(DepositTypeDto idTyplokata) {
        this.idTyplokata = idTyplokata;
    }

    @XmlTransient
    public Collection<DepositInterestDto> getLokataOdsetkiCollection() {
        return lokataOdsetkiCollection;
    }

    public void setLokataOdsetkiCollection(Set<DepositInterestDto> lokataOdsetkiCollection) {
        this.lokataOdsetkiCollection = lokataOdsetkiCollection;
    }

	public Set<UserDto> getOwners() {
		return owners;
	}

	public void setOwners(Set<UserDto> owners) {
		this.owners = owners;
	}

	public boolean isOdnawialny() {
		return odnawialny;
	}

	public void setOdnawialny(boolean odnawialny) {
		this.odnawialny = odnawialny;
	}

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}
    
}
