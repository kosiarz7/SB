package systemy.bankowe.scheduler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.cyclictransfer.ICyclicTransferDao;
import systemy.bankowe.dao.transfer.WaitingTransferDao;
import systemy.bankowe.dto.CyclicTransferDto;
import systemy.bankowe.dto.transfer.TransferType;
import systemy.bankowe.dto.transfer.WaitingTransfer;
import systemy.bankowe.flows.money.transfer.MoneyTransferResult;

public class MakeCyclicTransfers implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -7394623476751747627L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeCyclicTransfers.class);

    private ICyclicTransferDao cyclicTransferDao;
    
    private WaitingTransferDao waitingTransferDao;
    
    public void makeCyclicTransfers() {
        int dayOfMonth = getDayOfMonth();
        LOGGER.debug("makeCyclicTransfers|Entry time: {}, day of month: {}",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()), dayOfMonth);
        makeCyclicTransfers(dayOfMonth);
    }

    /**
     * Zwraca aktualny dzień miesiąca.
     * 
     * @return aktualny dzień miesiąca.
     */
    private int getDayOfMonth() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    /**
     * Wykonuje przelewy cykliczne z danego dnia.
     * 
     * @param dayOfMonth przelewy cykliczne z danego dnia.
     */
    public void makeCyclicTransfers(int dayOfMonth) {
        List<CyclicTransferDto> cyclicTransfers = cyclicTransferDao.getAllCyclicTransfersFromDay(dayOfMonth);
        for (CyclicTransferDto dto : cyclicTransfers) {
            makeTransfer(dto);
        }
        
        if (cyclicTransfers.isEmpty()) {
            LOGGER.debug("makeCyclicTransfers|Brak przelewów do wykonania dnia: {}", dayOfMonth);
        }
        else {
            LOGGER.debug("makeCyclicTransfers|Wykonano {} przelewów.", cyclicTransfers.size());
        }
    }

    private void makeTransfer(CyclicTransferDto cyclicTransfer) {
        WaitingTransfer transfer = new WaitingTransfer();
        transfer.setAmount(cyclicTransfer.getAmount());
        transfer.setAddress(cyclicTransfer.getCity());
        transfer.setDateWaitTransfer(new Date());
        transfer.setSenderAccountNumber(cyclicTransfer.getDebitAccount().getNumber());
        transfer.setTargetAccountNumber(cyclicTransfer.getAccountNumber());
        transfer.setTargetName(cyclicTransfer.getName());
        transfer.setTitle("Przelew cykliczny");
        transfer.setTransferType(new TransferType(TransferType.TransferTypeEnum.ONE_TIME_TRANSFER));
        int userId = cyclicTransfer.getUser().getId();
        int result = waitingTransferDao.submit(userId, transfer);
        LOGGER.debug("makeTransfer|Dane przelewu: {}", transfer);
        LOGGER.debug("makeTransfer|Kod: {}, wiadomość: {}", result, MoneyTransferResult.convertErrorCode(result));
    }

    public void setCyclicTransferDao(ICyclicTransferDao cyclicTransferDao) {
        this.cyclicTransferDao = cyclicTransferDao;
    }

    public void setWaitingTransferDao(WaitingTransferDao waitingTransferDao) {
        this.waitingTransferDao = waitingTransferDao;
    }
}