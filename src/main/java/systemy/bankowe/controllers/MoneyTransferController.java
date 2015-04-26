package systemy.bankowe.controllers;

import java.io.Serializable;

import systemy.bankowe.dao.transfer.WaitingTransfer;

public class MoneyTransferController implements Serializable{

	private static final long serialVersionUID = 1L;

	public boolean submit(final WaitingTransfer aTransfer)
	{
		
		return true;
	}
}
