package systemy.bankowe.controllers;

import java.io.Serializable;

import systemy.bankowe.dto.transfer.OrderToPay;

public class OrderToPayController  implements Serializable{

	private static final long serialVersionUID = 1L;

	public boolean submit(final OrderToPay aOrderToPay)
	{
		
		return true;
	}
}
