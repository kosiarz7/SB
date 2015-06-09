package systemy.bankowe.flows.money.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import systemy.bankowe.dao.transfer.OrderToPayDao;
import systemy.bankowe.dto.transfer.OrderToPay;
import systemy.bankowe.services.user.UserData;

public class OrderToPayFlow extends AbstractFlowHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	public OrderToPayResult submit(final OrderToPay aOrderToPay,
			SessionFactory factory, UserData user) {
		OrderToPayDao orderToPayDao = new OrderToPayDao();
		orderToPayDao.setSessionFactory(factory);
		int senderId = user.getUserDto().getId();
		int buf = orderToPayDao.submit(senderId, aOrderToPay);

		return new OrderToPayResult(OrderToPayResult.convertErrorCode(buf));
	}

	public List<OrderToPay> getOrderToPays(SessionFactory factory, String accountNumber) {
		if (accountNumber.isEmpty())
		{
			return new ArrayList<OrderToPay>();
		}
		OrderToPayDao orderToPayDao = new OrderToPayDao();
		orderToPayDao.setSessionFactory(factory);
		return orderToPayDao.getOrderToPays(accountNumber);
	}
	
	public List<OrderToPay> getOrderToPaysByUser(SessionFactory factory, UserData user)
	{
		OrderToPayDao orderToPayDao = new OrderToPayDao();
		orderToPayDao.setSessionFactory(factory);
		int senderId = user.getUserDto().getId();
		return orderToPayDao.getOrderToPaysByUser(senderId);
	}
}
