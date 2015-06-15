package systemy.bankowe.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;

@Entity
@Table(name = "KARTY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
	@DiscriminatorColumn(name = "card_type", discriminatorType = DiscriminatorType.STRING)
	@DiscriminatorValue(CardDiscriminator.BASE)
public class Card implements Serializable{

	/**
	 * UID
	 */
	private static final long serialVersionUID = -529893989224333146L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KARTY_SEQ")
    @SequenceGenerator(name = "KARTY_SEQ", sequenceName = "KARTY_SEQ", allocationSize = 1)
	private Integer id;
	
    @Column(name = "card_type", insertable = false, updatable = false)
    private String cardType;
	
	@Column(name = "card_number", nullable = false, length = 16)
	private String number;
	
	@Column(name = "secure_code", length = 4)
	private String secureCode;
	
	@Column(length = 4)
	private String pin;
	
	@Column(name = "activation_date")
	private Date activationDate;
	
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	private String label;
	
	/**
	 * Czy nie usunieta
	 */
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean enabled;
	
	/**
	 * Czy zablokowana
	 */
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean locked;
	
	/**
	 * Jak bardzo mozna wydoic konto karty
	 */
	private BigDecimal limit;
	
	/**
	 * Łączny dzienny limit (kwota)
	 */
	@Column(name = "dialy_limit")
	private BigDecimal summaryDialyMaxAmmount;
	
	/**
	 * Dzienny limit operacji gotówkowych (kwota)
	 */
	@Column(name = "dialy_cash_max_ammount")
	private BigDecimal cashDialyMaxAmmount;
	
	/**
	 * Dzienny limit operacji bezgotówkowych (kwota)
	 */
	@Column(name = "dialy_noncash_max_ammount")
	private BigDecimal noncashDailyMaxAmmount;
	
	/**
	 * Dzienny limit operacji zbliżeniowych (kwota)
	 */
	@Column(name = "dialy_proximity_max_ammount")
	private BigDecimal proximityDialyMaxAmmount;
	
	/**
	 * Dzienny limit operacji zbliżeniowych (liczba operacji)
	 */
	@Column(name = "dialy_proximity_max_ops")
	private Integer proximityDialyMaxOperations;
	
	/**
	 * Właściciel karty
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private UserDto owner;
	
	/**
	 * Rachunek karty
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private AccountDto account;
	
	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CardHistory> history = new ArrayList<CardHistory>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSecureCode() {
		return secureCode;
	}

	public void setSecureCode(String secureCode) {
		this.secureCode = secureCode;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	public BigDecimal getSummaryDialyMaxAmmount() {
		return summaryDialyMaxAmmount;
	}

	public void setSummaryDialyMaxAmmount(BigDecimal summaryDialyMaxAmmount) {
		this.summaryDialyMaxAmmount = summaryDialyMaxAmmount;
	}

	public BigDecimal getCashDialyMaxAmmount() {
		return cashDialyMaxAmmount;
	}

	public void setCashDialyMaxAmmount(BigDecimal cashDialyMaxAmmount) {
		this.cashDialyMaxAmmount = cashDialyMaxAmmount;
	}

	public BigDecimal getNoncashDailyMaxAmmount() {
		return noncashDailyMaxAmmount;
	}

	public void setNoncashDailyMaxAmmount(BigDecimal noncashDailyMaxAmmount) {
		this.noncashDailyMaxAmmount = noncashDailyMaxAmmount;
	}

	public BigDecimal getProximityDialyMaxAmmount() {
		return proximityDialyMaxAmmount;
	}

	public void setProximityDialyMaxAmmount(BigDecimal proximityDialyMaxAmmount) {
		this.proximityDialyMaxAmmount = proximityDialyMaxAmmount;
	}

	public Integer getProximityDialyMaxOperations() {
		return proximityDialyMaxOperations;
	}

	public void setProximityDialyMaxOperations(
			Integer proximityDialyMaxOperations) {
		this.proximityDialyMaxOperations = proximityDialyMaxOperations;
	}

	public UserDto getOwner() {
		return owner;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	public List<CardHistory> getHistory() {
		return history;
	}

	public void setHistory(List<CardHistory> history) {
		this.history = history;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", secureCode="
				+ secureCode + ", pin=" + pin + ", activationDate="
				+ activationDate + ", expirationDate=" + expirationDate
				+ ", enabled=" + enabled + ", locked=" + locked + ", limit="
				+ limit + ", summaryDialyMaxAmmount=" + summaryDialyMaxAmmount
				+ ", cashDialyMaxAmmount=" + cashDialyMaxAmmount
				+ ", noncashDailyMaxAmmount=" + noncashDailyMaxAmmount
				+ ", proximityDialyMaxAmmount=" + proximityDialyMaxAmmount
				+ ", proximityDialyMaxOperations="
				+ proximityDialyMaxOperations + ", owner=" + owner
				+ ", account=" + account + ", history=" + history + "]";
	}
	
}
