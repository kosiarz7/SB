package systemy.bankowe.dto.card;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import systemy.bankowe.dto.AccountDto;

@Entity
@DiscriminatorValue(CardDiscriminator.CHARGE_CARD)
public class ChargeCard extends Card {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 7463188764313931098L;
	
	@Column(name = "reporting_day")
	private int reportingDayOfTheWeek;
	
	@ManyToOne
	@JoinColumn(name = "account_to_charge")
	private AccountDto accountToCharge;
	
	private BigDecimal interest;
	
	

	public int getReportingDayOfTheWeek() {
		return reportingDayOfTheWeek;
	}

	public void setReportingDayOfTheWeek(int reportingDayOfTheWeek) {
		this.reportingDayOfTheWeek = reportingDayOfTheWeek;
	}

	public AccountDto getAccountToCharge() {
		return accountToCharge;
	}

	public void setAccountToCharge(AccountDto accountToCharge) {
		this.accountToCharge = accountToCharge;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChargeCard [reportingDayOfTheWeek=" + reportingDayOfTheWeek
				+ ", accountToCharge=" + accountToCharge + ", interest="
				+ interest + ", getId()=" + getId() + ", getNumber()="
				+ getNumber() + ", getSecureCode()=" + getSecureCode()
				+ ", getPin()=" + getPin() + ", getActivationDate()="
				+ getActivationDate() + ", getExpirationDate()="
				+ getExpirationDate() + ", isEnabled()=" + isEnabled()
				+ ", isLocked()=" + isLocked() + ", getLimit()=" + getLimit()
				+ ", getSummaryDialyMaxAmmount()="
				+ getSummaryDialyMaxAmmount() + ", getCashDialyMaxAmmount()="
				+ getCashDialyMaxAmmount() + ", getNoncashDailyMaxAmmount()="
				+ getNoncashDailyMaxAmmount()
				+ ", getProximityDialyMaxAmmount()="
				+ getProximityDialyMaxAmmount()
				+ ", getProximityDialyMaxOperations()="
				+ getProximityDialyMaxOperations() + ", getOwner()="
				+ getOwner() + ", getAccount()=" + getAccount()
				+ ", getHistory()=" + getHistory() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
