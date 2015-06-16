package systemy.bankowe.dto.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OPERACJE_KARTA")
public class CardOperation implements Serializable{


	/**
	 * UID
	 */
	private static final long serialVersionUID = 6389717351428886123L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERACJE_KARTA_SEQ")
    @SequenceGenerator(name = "OPERACJE_KARTA_SEQ", sequenceName = "OPERACJE_KARTA_SEQ", allocationSize = 1)
	private Integer id;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		
        for (Map.Entry<String, String> entry : CardOperationType.getAllTypes().entrySet()) {
            if (entry.getValue().equals(this.cardOperationType)) {
                return entry.getKey();
            }
        }
        return this.cardOperationType;
	}

	
	
}
