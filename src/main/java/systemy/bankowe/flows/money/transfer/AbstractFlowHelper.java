package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.services.user.UserData;

public class AbstractFlowHelper implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    protected String splitAccountNumber(String accountNumber)
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
    
    public String convertDateToString(Date date)
    {
    	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    	return format.format(date);
    }
}
