package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;

import systemy.bankowe.dao.transfer.OrderToPayDao;
import systemy.bankowe.dto.transfer.OrderToPay;
import systemy.bankowe.services.accountnumber.IAccountNumberService;

public abstract class OrderToPayBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6789711125409284246L;
	
	protected OrderToPay selectedOrderToPay;
	protected OrderToPayDao orderToPayDao;
	protected List<OrderToPay> ordersToPay;
	protected String accountNumber;
	protected IAccountNumberService accountService;
	 
	public List<OrderToPay> getOrdersToPay() {
		return ordersToPay;
	}

	public void setOrdersToPay(List<OrderToPay> ordersToPay) {
		this.ordersToPay = ordersToPay;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setAccountService(IAccountNumberService accountService) {
		this.accountService = accountService;
	}

	public void selectedAccountChanged(ValueChangeEvent event) {
		String buf = (String) event.getNewValue();
		accountNumber = accountService.removeWhitespaces(buf);
		ordersToPay = reloadOrderToPays(accountNumber);
    }
	
	public void setOrderToPayDao(OrderToPayDao orderToPayDao) {
		this.orderToPayDao = orderToPayDao;
	}
	
	public String formatAccountNumber(String accountNumber)
	{
		return accountService.formatAccountNumber(accountNumber);
	}
	
	public void terminateOrderToPay()
	{
		selectedOrderToPay.setToDate(new Date());
		orderToPayDao.updateOrderToPay(selectedOrderToPay);
		ordersToPay = reloadOrderToPays(accountNumber);
	}
	
	public void  updateOrderToPay()
	{
		orderToPayDao.updateOrderToPay(selectedOrderToPay);
		ordersToPay = orderToPayDao.getOrderToPays(accountNumber);
	}
	public void onRowSelect(SelectEvent event) {
		Object obj = event.getObject();
		selectedOrderToPay= (OrderToPay) obj;
    }
	
	
	public OrderToPay getSelectedOrderToPay() {
		return selectedOrderToPay;
	}

	public void setSelectedOrderToPay(OrderToPay selectedOrderToPay) {
		this.selectedOrderToPay = selectedOrderToPay;
	}
	
	public abstract List<OrderToPay> reloadOrderToPays(String accountNumber);

	
}