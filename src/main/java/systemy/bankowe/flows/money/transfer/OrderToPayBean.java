package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;

import systemy.bankowe.common.beans.SpringSecurityContextUtilBean;
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
	protected SpringSecurityContextUtilBean springSecurityContextUtilBean;
	protected String selectedEmpoweredAccountFormat;
	protected String selectedAccountFormat;

	protected OrderToPayResult editOrderToPayResult;
	
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
		if (accountNumber == null)
		{
			return new String();
		}
		return accountService.formatAccountNumber(accountNumber);
	}
	
	public void terminateOrderToPay()
	{
		selectedOrderToPay.setToDate(new Date());
		int senderId = springSecurityContextUtilBean.getLoggedInUser().get().getUserDto().getId();
		System.err.println(selectedOrderToPay.toString());
		selectedOrderToPay.setAccountNumber(accountNumber);
		int res = orderToPayDao.updateOrderToPay(senderId, selectedOrderToPay);
		editOrderToPayResult = new OrderToPayResult(OrderToPayResult.convertErrorCode(res));
		ordersToPay = reloadOrderToPays(accountNumber);
	}
	
	public void  updateOrderToPay()
	{
		selectedOrderToPay.setAccountEmpowered(selectedEmpoweredAccountFormat);
		int senderId = springSecurityContextUtilBean.getLoggedInUser().get().getUserDto().getId();
		System.err.println(selectedOrderToPay.toString());
		selectedOrderToPay.setAccountNumber(accountNumber);
		int res = orderToPayDao.updateOrderToPay(senderId, selectedOrderToPay);
		editOrderToPayResult = new OrderToPayResult(OrderToPayResult.convertErrorCode(res));
		ordersToPay = orderToPayDao.getOrderToPays(accountNumber);
	}
	public SpringSecurityContextUtilBean getSpringSecurityContextUtilBean() {
		return springSecurityContextUtilBean;
	}

	public void setSpringSecurityContextUtilBean(
			SpringSecurityContextUtilBean springSecurityContextUtilBean) {
		this.springSecurityContextUtilBean = springSecurityContextUtilBean;
	}

	public void onRowSelect(SelectEvent event) {
		Object obj = event.getObject();
		selectedOrderToPay= (OrderToPay) obj;
		selectedEmpoweredAccountFormat = accountService.formatAccountNumber(selectedOrderToPay.getAccountEmpowered());
		//selectedAccountFormat = accountService.formatAccountNumber(selectedOrderToPay.getAccountNumber());
    }
	
	
	public String getSelectedEmpoweredAccountFormat() {
		return selectedEmpoweredAccountFormat;
	}

	public void setSelectedEmpoweredAccountFormat(
			String selectedEmpoweredAccountFormat) {
		this.selectedEmpoweredAccountFormat = selectedEmpoweredAccountFormat;
	}

	public OrderToPay getSelectedOrderToPay() {
		return selectedOrderToPay;
	}

	public void setSelectedOrderToPay(OrderToPay selectedOrderToPay) {
		this.selectedOrderToPay = selectedOrderToPay;
	}
	
	public abstract List<OrderToPay> reloadOrderToPays(String accountNumber);

	public OrderToPayResult getEditOrderToPayResult() {
		return editOrderToPayResult;
	}

	public void setEditOrderToPayResult(OrderToPayResult editOrderToPayResult) {
		this.editOrderToPayResult = editOrderToPayResult;
	}

	
}
