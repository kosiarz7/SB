package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.List;

import systemy.bankowe.dto.transfer.OrderToPay;

public class OrderToPayShowBean extends OrderToPayBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6789711125409284246L;

	public List<OrderToPay> reloadOrderToPays(String accountNumber)
	{
		return orderToPayDao.getOrderToPays(accountNumber);
	}

}
