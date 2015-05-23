package systemy.bankowe.dto.card;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CardDiscriminator.DEBIT_CARD)
public class DebitCard extends Card {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 4361689509933091314L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
