package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import systemy.bankowe.common.beans.SpringSecurityContextUtilBean;
import systemy.bankowe.dao.transfer.RealizedTransferDao;
import systemy.bankowe.dto.transfer.AbstractTransfer;
import systemy.bankowe.services.accountnumber.IAccountNumberService;

public class MoneyTransferShowBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAccountNumberService accountService;
	private String accountNumber;
	private RealizedTransferDao realizedTransferDao;
	private List<AbstractTransfer> realizedTransfers;
	protected SpringSecurityContextUtilBean springSecurityContextUtilBean;

	public void selectedAccountChanged(ValueChangeEvent event) {
		String accountNumber = (String) event.getNewValue();
		loadTransfers(accountNumber);
	}

	public void loadTransfers(String accountNumber)
	{
		accountNumber = accountService.removeWhitespaces(accountNumber);
		realizedTransfers = realizedTransferDao.getOutcomingTransfers(accountNumber);
	}
	public IAccountNumberService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountNumberService accountService) {
		this.accountService = accountService;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public RealizedTransferDao getRealizedTransferDao() {
		return realizedTransferDao;
	}

	public void setRealizedTransferDao(RealizedTransferDao realizedTransferDao) {
		this.realizedTransferDao = realizedTransferDao;
	}

	public List<AbstractTransfer> getRealizedTransfers() {
		return realizedTransfers;
	}

	public void setRealizedTransfers(List<AbstractTransfer> realizedTransfers) {
		this.realizedTransfers = realizedTransfers;
	}

	public SpringSecurityContextUtilBean getSpringSecurityContextUtilBean() {
		return springSecurityContextUtilBean;
	}

	public void setSpringSecurityContextUtilBean(
			SpringSecurityContextUtilBean springSecurityContextUtilBean) {
		this.springSecurityContextUtilBean = springSecurityContextUtilBean;
	}

}
