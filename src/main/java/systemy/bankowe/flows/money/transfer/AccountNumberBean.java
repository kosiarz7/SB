package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import systemy.bankowe.dao.transfer.OrderToPayDao;
import systemy.bankowe.dto.transfer.OrderToPay;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.util.CollectionsUtil;

public class AccountNumberBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6789711125409284246L;
	
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
		accountNumber = (String) event.getNewValue();
		System.err.println("old: " + event.getOldValue() + " new: " + event.getNewValue());
		ordersToPay = orderToPayDao.getOrderToPays(accountService.removeWhitespaces(accountNumber));
		System.err.println("size: " + (null != ordersToPay ? ordersToPay.size() : "null ptr"));
		System.err.println("orders: " + CollectionsUtil.toString(ordersToPay));
    }
	
	public void setOrderToPayDao(OrderToPayDao orderToPayDao) {
		this.orderToPayDao = orderToPayDao;
	}
	
	private OrderToPayDao orderToPayDao;
	List<OrderToPay> ordersToPay;
	String accountNumber;
	private IAccountNumberService accountService;

}
