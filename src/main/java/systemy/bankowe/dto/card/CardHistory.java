package systemy.bankowe.dto.card;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORIA_KART")
public class CardHistory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORIA_KART_SEQ")
    @SequenceGenerator(name = "HISTORIA_KART_SEQ", sequenceName = "HISTORIA_KART_SEQ", allocationSize = 1)
	private Integer id;
	
	private Date timestamp;
	
	private BigDecimal ammount;
	
	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;
	
	@ManyToOne
	@JoinColumn(name = "operation_id")
	private CardOperation operation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getAmmount() {
		return ammount;
	}

	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public CardOperation getOperation() {
		return operation;
	}

	public void setOperation(CardOperation operation) {
		this.operation = operation;
	}
	
	

}
