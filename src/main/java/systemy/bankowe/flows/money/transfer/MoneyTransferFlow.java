package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.transfer.WaitingTransferDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.DefinedRecipientDto;
import systemy.bankowe.dto.transfer.AbstractTransfer;
import systemy.bankowe.dto.transfer.RealizedTransfer;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.UnrealizedTransfer;
import systemy.bankowe.dto.transfer.WaitingTransfer;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.util.StringUtil;

public class MoneyTransferFlow extends AbstractFlowHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferFlow.class);
    
    private List<DefinedRecipientDto> recipients;
    
    public boolean isDefinedRecipeintsAvailable() {
        if (null != recipients) {
            return !recipients.isEmpty();
        }
        return false;
    }
    
    public void init(SpringSecurityContextUtil loginUserService, String targetAccountNumber,
            WaitingTransfer waitingTransfer) {
        LOGGER.debug("init|targetAccountNumber: {}", targetAccountNumber);
        recipients = loginUserService.getAllDefinedRecipients();
        if (!StringUtil.isEmpty(targetAccountNumber)) {
            waitingTransfer.setSenderAccountNumber(splitAccountNumber(targetAccountNumber));
        }
    }
    
    public void selectRecipientListener(DefinedRecipientDto selectedRecipient, WaitingTransfer waitingTransfer) {
        waitingTransfer.setTargetName(selectedRecipient.getName());
        waitingTransfer.setAddress(selectedRecipient.getStreet() + " " + selectedRecipient.getStreetNo() + " "
                + selectedRecipient.getCity() + " " + selectedRecipient.getZipcode());
        waitingTransfer.setTargetAccountNumber(splitAccountNumber(selectedRecipient.getAccountNumber()));
    }

    public MoneyTransferResult sendMoneyTransfer(final WaitingTransfer aTransfer, SessionFactory factory, UserData user) {

        WaitingTransferDao waitingTransfer = new WaitingTransferDao();
        aTransfer.setTransferType(new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER));
        waitingTransfer.setSessionFactory(factory);
        int senderId = user.getUserDto().getId();
        int buf = waitingTransfer.submit(senderId, aTransfer);
        
        return new MoneyTransferResult(MoneyTransferResult.convertErrorCode(buf));
    }

    public void loadTransfers(UserData user, MoneyTransferShowBean bean) {
      AccountDto accountDto = user.getUserDto().getAccounts().iterator().next();
      bean.loadTransfers(accountDto.getNumber());
    }


    public List<DefinedRecipientDto> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<DefinedRecipientDto> recipients) {
        this.recipients = recipients;
    }
    
    public String convertTypeToString(AbstractTransfer transfer)
    {
    	if (transfer instanceof RealizedTransfer)
    	{
    		return "Zrealizowany";
    	}
    	else if (transfer instanceof UnrealizedTransfer)
    	{
    		UnrealizedTransfer unrealizedTransfer = (UnrealizedTransfer) transfer;
    		return "Niezrealizowany: " + MoneyTransferResult.convertErrorCode(unrealizedTransfer.getErrorCode());
    	}
    	else 
    	{
    		return "Przychodzący";
    	}
    }
}
