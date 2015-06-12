package systemy.bankowe.dao.transfer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.transfer.OrderToPay;

public class OrderToPayDao extends HibernateUtil {

	private static final long serialVersionUID = 1L;
/*
CREATE OR REPLACE PROCEDURE pr_dodaj_polecenie(	res out int,
												i_id_klient IN INT,
												i_nazwa IN VARCHAR2,
												i_rachunek IN VARCHAR2,
												i_rachunek_upow IN VARCHAR2,
												i_od_kiedy IN VARCHAR2,
												i_do_kiedy IN VARCHAR2,
												i_maksymalna_kwota IN DECIMAL(*, 5)
												)
 */
	public int submit(int senderId, OrderToPay orderToPay)
	{
		orderToPay.removeSpaceFromAccountNumber();
		Session session = openSession();

        session.getTransaction().begin();
        
        String pattern = "CALL pr_dodaj_polecenie(?, ?, ?, ?, ?, ?, ?, ?)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateFromStr = dateFormat.format(orderToPay.getFromDate());
        String dateToStr = dateFormat.format(orderToPay.getToDate());

        String name = orderToPay.getName();
        String accountNumber = orderToPay.getAccountNumber();
        String accountNumberEmpowered = orderToPay.getAccountEmpowered();
        double maxAmount = orderToPay.getMaxAmount();
        try {
            // znowu cos zrobili deprecated w interface Session. Taki myk by to ominac :P
            CallableStatement callableStatement = ((SessionImpl) session).connection().prepareCall(pattern);

            callableStatement.setInt(2, senderId);
            callableStatement.setString(3, name);
            callableStatement.setString(4, accountNumber);
            callableStatement.setString(5, accountNumberEmpowered);
            callableStatement.setString(6, dateFromStr);
            callableStatement.setString(7, dateToStr);
            callableStatement.setDouble(8, maxAmount);
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.execute();
            Integer resultInt = (Integer) callableStatement.getObject(1);
            callableStatement.getFetchSize();
            session.getTransaction().commit();
            
            return resultInt;

        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        session.getTransaction().rollback();
        return -1;
	}
	@SuppressWarnings("unchecked")
	public List<OrderToPay> getOrderToPays(String accountNumber) {
		Session session = openSession();

        session.getTransaction().begin();
        SQLQuery query = session.createSQLQuery("SELECT upowaznienie_polecenia_zapl.* from upowaznienie_polecenia_zapl inner join rachunki on rachunki.id_rachunek = upowaznienie_polecenia_zapl.id_rachunek where rachunki.numer=:nr and upowaznienie_polecenia_zapl.do_kiedy >= sysdate");
        query.addEntity(OrderToPay.class);
        query.setString("nr", accountNumber);
        List<OrderToPay> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderToPay> getOrderToPaysByUser(int userId) {
		Session session = openSession();

        SQLQuery query = session.createSQLQuery(
        		"SELECT upowaznienie_polecenia_zapl.* from upowaznienie_polecenia_zapl inner join klienci on klienci.id_klient = upowaznienie_polecenia_zapl.id_klient where klienci.id_klient = :id");
        query.addEntity(OrderToPay.class);
        query.setInteger("id", userId);
        List<OrderToPay> list = query.list();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderToPay> getOrderToPaysUseByUser(int userIdThatCanOrderToPay)
	{
		Session session = openSession();

        SQLQuery query = session.createSQLQuery(
        		"SELECT zap.* from upowaznienie_polecenia_zapl  zap, klienci  kl, rachunki  r, klienci_rachunki  kl_r " +
        		"WHERE zap.nr_rachunku_upowaznionego = r.numer AND r.id_rachunek = kl_r.id_rachunek AND kl_r.id_klient = kl.id_klient AND kl.id_klient = :id");
        query.addEntity(OrderToPay.class);
        query.setInteger("id", userIdThatCanOrderToPay);
        List<OrderToPay> list = query.list();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderToPay> getOrderToPaysUseByAccountNumber(String accountNumber)
	{
		Session session = openSession();
		System.err.println("account: "+accountNumber);
        SQLQuery query = session.createSQLQuery(
        		"SELECT zap.* from upowaznienie_polecenia_zapl  zap, klienci  kl, rachunki  r, klienci_rachunki  kl_r " +
        		"WHERE zap.nr_rachunku_upowaznionego = r.numer AND r.id_rachunek = kl_r.id_rachunek AND kl_r.id_klient = kl.id_klient AND r.numer = :account");
        query.addEntity(OrderToPay.class);
        query.setString("account", accountNumber);
        List<OrderToPay> list = query.list();
        System.err.println("list size: "+list.toString());
        return list;
	}
	
	public void updateOrderToPay(OrderToPay orderToPay)
	{
		Session session = openSession();

        session.getTransaction().begin();
        session.update(orderToPay);
        session.getTransaction().commit();
        
	}
}
