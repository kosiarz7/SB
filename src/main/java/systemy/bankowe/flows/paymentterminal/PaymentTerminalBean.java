package systemy.bankowe.flows.paymentterminal;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import systemy.bankowe.dto.card.CardOperationType;
import systemy.bankowe.services.card.ICardService;
import systemy.bankowe.services.card.Result;

public class PaymentTerminalBean implements Serializable{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 6331188292340875389L;
	private String lastError = null;
	
	@Autowired
	ICardService cardService;
	
	public Map<String, String> getOperationTypes() {
		return CardOperationType.getAllTypes();
	}
	
	public String getLastError() {
		String error = lastError;
		lastError = null;
		return error;
	}

	public void setLastError(String lastError) {
		this.lastError = lastError;
	}


	public boolean pay(PaymentTerminalData data) {
				
		Result result = cardService.pay(data);
		if (result.isFail())
			setLastError(result.getCause());
		
		return !result.isFail();
	}

}
