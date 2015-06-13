package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.List;

import systemy.bankowe.dao.transfer.WaitingTransferDao;
import systemy.bankowe.dto.transfer.OrderToPay;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.TransferType.TransferTypeEnum;
import systemy.bankowe.dto.transfer.WaitingTransfer;
import systemy.bankowe.services.user.UserData;

public class OrderToPayUseBean extends OrderToPayBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6789711125409284246L;

	private WaitingTransfer waitingTransfer = new WaitingTransfer();
	private WaitingTransferDao waitingTransferDao;
	private MoneyTransferResult moneyTransferResult;
	
	
	public List<OrderToPay> reloadOrderToPays(String accountNumber)
	{
		waitingTransfer = new WaitingTransfer();
	
		return orderToPayDao.getOrderToPaysUseByAccountNumber(accountNumber);
	}

	public WaitingTransfer getWaitingTransfer() {
		return waitingTransfer;
	}

	public void setWaitingTransfer(WaitingTransfer waitingTransfer) {
		this.waitingTransfer = waitingTransfer;
	}
	

	public WaitingTransferDao getWaitingTransferDao() {
		return waitingTransferDao;
	}

	public void setWaitingTransferDao(WaitingTransferDao waitingTransferDao) {
		this.waitingTransferDao = waitingTransferDao;
	}

	public void addWaitingTransfer(UserData user)
	{
		//waitingTransfer
		
		waitingTransfer.setAddress(user.getUserDto().getAddress());
		waitingTransfer.setTargetName(user.getNameAndSurname());
		waitingTransfer.setSenderAccountNumber(selectedOrderToPay.getAccount().getNumber());
		waitingTransfer.setTargetAccountNumber(this.accountNumber);
		waitingTransfer.setTransferType(new TransferType(TransferTypeEnum.ORDER_TO_PAY));

		int id = user.getUserDto().getId();
		int res = waitingTransferDao.submit(id, waitingTransfer);
		moneyTransferResult = new MoneyTransferResult(MoneyTransferResult.convertErrorCode(res));
	}

	public MoneyTransferResult getMoneyTransferResult() {
		return moneyTransferResult;
	}

	public void setMoneyTransferResult(MoneyTransferResult moneyTransferResult) {
		this.moneyTransferResult = moneyTransferResult;
	}
	
	
}
