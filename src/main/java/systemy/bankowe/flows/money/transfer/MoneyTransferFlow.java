package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import systemy.bankowe.dao.transfer.WaitingTransferDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.dto.transfer.OutcomingTransfer;
import systemy.bankowe.dto.transfer.RealizedTransfer;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.WaitingTransfer;
import systemy.bankowe.services.user.UserData;

public class MoneyTransferFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    public MoneyTransferResult sendMoneyTransfer(final WaitingTransfer aTransfer, SessionFactory factory, UserData user) {
        WaitingTransferDao waitingTransfer = new WaitingTransferDao();
        aTransfer.setTransferType(new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER));
        waitingTransfer.setSessionFactory(factory);
        int senderId = user.getUserDto().getId();
        int buf = waitingTransfer.submit(senderId, aTransfer);
        
        return new MoneyTransferResult(MoneyTransferResult.convertErrorCode(buf));
    }

    public List<WaitingTransfer> getWaitingTransfers(UserData user) {
        ArrayList<WaitingTransfer> list = new ArrayList<WaitingTransfer>();
        return list;
    }

    public List<RealizedTransfer> getRealizedTransfers(UserData user) {
        ArrayList<RealizedTransfer> list = new ArrayList<RealizedTransfer>();
        return list;
    }

    public List<OutcomingTransfer> getOutcomingTransfers(UserData user) {
        ArrayList<OutcomingTransfer> list = new ArrayList<OutcomingTransfer>();
        return list;
    }
    
    public List<String> getAccountsStringList(UserData user)
    {
        UserDto userDto = user.getUserDto();
        List<AccountDto> accounts = userDto.getAccounts();
        ArrayList<String> accountNumberStrings = new ArrayList<String>(accounts.size());
        for (AccountDto account : accounts)
        {
            String numberStr = account.getNumber();
            String numberStrWithSpaces = splitAccountNumber(numberStr);
            accountNumberStrings.add(numberStrWithSpaces);
        }
        return accountNumberStrings;
    }
    
    private String splitAccountNumber(String accountNumber)
    {
        StringBuilder str =new StringBuilder();
        str.append(accountNumber.substring(0, 2));
        for (int i = 2 ; i < accountNumber.length(); ++i)
        {
            if ((i - 2) % 4 == 0)
            {
                str.append(" ");
            }
            str.append(accountNumber.charAt(i));
        }
        return str.toString();
    }
    
}
