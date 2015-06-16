package systemy.bankowe.dao.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import systemy.bankowe.dao.HibernateUtil;
import systemy.bankowe.dto.transfer.AbstractTransfer;
import systemy.bankowe.dto.transfer.IncomingTransfer;
import systemy.bankowe.dto.transfer.RealizedTransfer;
import systemy.bankowe.dto.transfer.UnrealizedTransfer;

public class RealizedTransferDao extends HibernateUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<AbstractTransfer> getOutcomingTransfers(String accountNumber)
	{
		
        String realizedSQL = "SELECT przelew_wych_zrealizowany.* "
        		+ "FROM przelew_wych_zrealizowany, rachunki "
        		+ "WHERE przelew_wych_zrealizowany.ID_RACHUNKU_ZRODLA = rachunki.ID_RACHUNEK AND rachunki.numer = :account "
        		+ "ORDER BY przelew_wych_zrealizowany.data_realizacji DESC ";
        
        Session session = openSession();

        session.getTransaction().begin();
        SQLQuery realizedQuery = session.createSQLQuery(realizedSQL);
        realizedQuery.addEntity(RealizedTransfer.class);
        realizedQuery.setString("account", accountNumber);
        @SuppressWarnings("unchecked")
		List<RealizedTransfer> realizedTransfers = realizedQuery.list();
        
        String unrealizedSQL = "SELECT przelew_niezrealizowany.* "
        		+ "FROM przelew_niezrealizowany, rachunki "
        		+ "WHERE przelew_niezrealizowany.ID_RACHUNKU_ZRODLA = rachunki.ID_RACHUNEK AND rachunki.numer = :account "
        		+ "ORDER BY przelew_niezrealizowany.data_realizacji DESC ";
        
        SQLQuery unrealizedQuery = session.createSQLQuery(unrealizedSQL);
        unrealizedQuery.addEntity(UnrealizedTransfer.class);
        unrealizedQuery.setString("account", accountNumber);
        @SuppressWarnings("unchecked")
		List<UnrealizedTransfer> unrealizedTransfers = unrealizedQuery.list();
        
        String incomingSQL = "SELECT przelew_przychodzacy.* FROM przelew_przychodzacy, rachunki WHERE przelew_przychodzacy.id_rachunek_docelowy = rachunki.id_rachunek AND  rachunki.numer = :account ORDER BY przelew_przychodzacy.data_realizacji DESC";
     
        SQLQuery incomingQuery = session.createSQLQuery(incomingSQL);
        incomingQuery.addEntity(IncomingTransfer.class);
        incomingQuery.setString("account", accountNumber);
        @SuppressWarnings("unchecked")
		List<IncomingTransfer> incomingTransfers = incomingQuery.list();
        
        List<AbstractTransfer> outcomingTransfers = new ArrayList<AbstractTransfer>(realizedTransfers.size() + unrealizedTransfers.size());
        outcomingTransfers.addAll(realizedTransfers);
        outcomingTransfers.addAll(unrealizedTransfers);
        outcomingTransfers.addAll(incomingTransfers);
        
        return outcomingTransfers;
	}
}
