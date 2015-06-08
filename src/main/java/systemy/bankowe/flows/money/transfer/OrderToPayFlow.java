package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import systemy.bankowe.dto.transfer.OrderToPay;
import systemy.bankowe.services.user.UserData;

public class OrderToPayFlow  implements Serializable{

	private static final long serialVersionUID = 1L;

	public boolean submit(final OrderToPay aOrderToPay)
	{
		
		return true;
	}
	
	public List<OrderToPay> getOrderToPays(UserData user)
	{
	    System.err.println(user.getNameAndSurname());
	    ArrayList<OrderToPay> list = new ArrayList<OrderToPay>();
	    list.add(new OrderToPay(1, "Poleceni1", "01231231321231231231231231123", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 1000));
	    list.add(new OrderToPay(2, "Poleceni2", "24234153465234532452345234524", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 1000));
	    list.add(new OrderToPay(3, "Poleceni3", "35345237676575675678678678678", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 1000));
        
	    return list;
	}
}
