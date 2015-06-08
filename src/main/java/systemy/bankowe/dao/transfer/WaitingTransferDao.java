package systemy.bankowe.dao.transfer;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.WaitingTransfer;

public class WaitingTransferDao extends HibernateUtil {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public int submit(int senderId, WaitingTransfer waitingTransfer) {
    	
        waitingTransfer.removeSpaceFromAccountNumber();
        Session session = openSession();

        session.getTransaction().begin();
        // najpierw pobierz id typu przelewu - i co z tego ze na okolo :p

        String hql = "FROM TransferType WHERE typeName = '"
                + waitingTransfer.getTransferType().getTransferTypeEnum().sqlValue() + "'";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
        List<TransferType> results = (List<TransferType>) query.list();
        TransferType type = results.iterator().next();
        waitingTransfer.setTransferType(type);

        String pattern = "CALL fn_dodaj_przelew2(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = dateFormat.format(waitingTransfer.getRealizationDate());

        String senderAccount = waitingTransfer.getSenderAccountNumber();
        String targetAccount = waitingTransfer.getTargetAccountNumber();
        String targetName = waitingTransfer.getTargetName();
        String address = waitingTransfer.getAddress();
        String title = waitingTransfer.getTitle();
        double amount = waitingTransfer.getAmount();
        TransferType transerType = waitingTransfer.getTransferType();
        try {
            // znowu cos zrobili deprecated w interface Session. Taki myk by to ominac :P
            CallableStatement callableStatement = ((SessionImpl) session).connection().prepareCall(pattern);

            callableStatement.setInt(2, senderId);
            callableStatement.setString(3, senderAccount);
            callableStatement.setString(4, targetAccount);
            callableStatement.setString(5, targetName);
            callableStatement.setString(6, address);
            callableStatement.setString(7, title);
            callableStatement.setDouble(8, amount);
            callableStatement.setString(9, dateStr);
            callableStatement.setInt(10, transerType.getId());
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.execute();
            Integer resultInt = (Integer) callableStatement.getObject(1);
            callableStatement.getFetchSize();

            //// Powinno byc uruchomione raz iles tam ale w ramach testow przelewy beda realizowane na biezaco
            String realizationPattern = "CALL pr_przetwarzanie_przel_oczek(sysdate)";
            CallableStatement realizationCallableStatement = ((SessionImpl) session).connection().prepareCall(realizationPattern);
            realizationCallableStatement.execute();
            session.getTransaction().commit();
            
            return resultInt;

        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;

    }
}
