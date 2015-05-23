package systemy.bankowe.dto.card;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "OPERACJE_KARTA")
public class CardOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * Rodzaj karty
	 */
	@Column(name = "card_discriminator")
	private String cardDiscriminator;
	
	/**
	 * Rodzaj operacji
	 */
	@Column(name = "card_operation_type")
	private String cardOperationType;
	
	/**
	 * koszty stałe
	 */
	private BigDecimal price;
	
	/**
	 * prowizja od kwoty
	 */
	private BigDecimal interest;
	
	@OneToMany(mappedBy = "operation")
	private Set<CardHistory> history;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardDiscriminator() {
		return cardDiscriminator;
	}

	public void setCardDiscriminator(String cardDiscriminator) {
		this.cardDiscriminator = cardDiscriminator;
	}

	public String getCardOperationType() {
		return cardOperationType;
	}

	public void setCardOperationType(String cardOperationType) {
		this.cardOperationType = cardOperationType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Set<CardHistory> getHistory() {
		return history;
	}

	public void setHistory(Set<CardHistory> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "CardOperation [id=" + id + ", cardDiscriminator="
				+ cardDiscriminator + ", cardOperationType="
				+ cardOperationType + ", price=" + price + ", interest="
				+ interest + ", history=" + history + "]";
	}

	
}
