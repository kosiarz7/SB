package systemy.bankowe.dto.card;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CardDiscriminator.CREDIT_CARD)
public class CreditCard extends Card {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1439662615043348854L;

	@Column(name = "reporting_day")
	private int reportingDayOfTheWeek;
	
	@Column(name = "interest_free_days")
	private int interestFreeDays;

	private BigDecimal interest;
	


	public int getReportingDayOfTheWeek() {
		return reportingDayOfTheWeek;
	}

	public void setReportingDayOfTheWeek(int reportingDayOfTheWeek) {
		this.reportingDayOfTheWeek = reportingDayOfTheWeek;
	}

	public int getInterestFreeDays() {
		return interestFreeDays;
	}

	public void setInterestFreeDays(int interestFreeDays) {
		this.interestFreeDays = interestFreeDays;
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
	
	
}
