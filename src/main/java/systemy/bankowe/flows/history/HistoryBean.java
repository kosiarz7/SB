package systemy.bankowe.flows.history;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.math3.util.Precision;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.pdf.IPdfGeneratorService;
import systemy.bankowe.services.pdf.PdfGenerationException;
import systemy.bankowe.services.pdf.SingleTransferDetails;
import systemy.bankowe.services.pdf.TransferSummaryData;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;

public class HistoryBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -3214942146087644795L;
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryBean.class);
    private IPdfGeneratorService pdfService;
    private IAccountNumberService accountNumberService;
    private IUserService userService;
    private SpringSecurityContextUtil loginUserUtil;
    
    public void init(final HistoryDataBean historyData, final String accountNumber) {
        LOGGER.debug("init|account number: {}", accountNumber);
        historyData.setCurrentAccountNumber(accountNumber);
        Date end = new Date();
        GregorianCalendar begin = new GregorianCalendar();
        begin.setTime(end);
        begin.add(Calendar.MONTH, -1);
        historyData.setFrom(begin.getTime());
        historyData.setTo(end);
        searchTransfers(historyData);
    }
    
    private void calculateAmounts(final HistoryDataBean historyData, List<TransferDataBean> transfers) {
        if (!transfers.isEmpty()) {
            historyData.setBeginSaldo(transfers.get(0).getSaldo());
            historyData.setEndSaldo(transfers.get(transfers.size() - 1).getSaldo());
            historyData.setSaldo(historyData.getEndSaldo() - historyData.getBeginSaldo());
            
            double credits = 0.0, debits = 0.0;
            for (TransferDataBean t : transfers) {
                if (t.isDebit()) {
                    debits += t.getAmmount();
                }
                else {
                     credits += t.getAmmount();
                }
            }
            
            historyData.setDebits(debits);
            historyData.setCredits(credits);
        }
    }
    
    public boolean validateDates(HistoryDataBean historyData) {
        Date present = new Date();
        
        if (present.before(historyData.getTo())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data \"do\" nie może być przyszła.",
                            "Data \"do\" nie może być przyszła."));
            return false;
        }
        if (historyData.getFrom().after(historyData.getTo())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data \"od\" nie może być późniejsza niż data \"do\".",
                            "Data \"od\" nie może być późniejsza niż data \"do\""));
            return false;
        }
        
        return true;
    }
    
    public void searchTransfers(final HistoryDataBean historyData) {
        LOGGER.debug("searchTransfers|Data: {}", historyData);
        List<TransferDataBean> transfers = getUserTransfers(historyData.getFrom(), historyData.getTo(),
                historyData.getCurrentAccountNumber());
        calculateAmounts(historyData, transfers);
        historyData.setTransferData(transfers);
        historyData.setSaldoChart(createSaldoChart(transfers, historyData.getFrom(), historyData.getTo()));
    }
    
    public StreamedContent getSummaryPdf(final HistoryDataBean historyData) {
        try {
            TransferSummaryData data = createTransferSummaryData(historyData);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfService.generateTransferSummaryPdf(data));
            return new DefaultStreamedContent(inputStream, "application/pdf", createSummaryFileName(data));
        }
        catch (PdfGenerationException e) {
            LOGGER.error("getSummaryPdf|Wystąpil błąd podczas tworzenia pdfa z zestawieniem operacji.", e);
            throw new RuntimeException(e);
        }
    }
    
    private TransferSummaryData createTransferSummaryData(final HistoryDataBean historyData) {
        TransferSummaryData data = new TransferSummaryData();
        data.setBegin(historyData.getFrom());
        data.setEnd(historyData.getTo());
        data.setBeginAmount(historyData.getBeginSaldo());
        data.setEndAmount(historyData.getEndSaldo());
        data.setCredits(historyData.getCredits());
        data.setDebits(historyData.getDebits());
        data.setSaldo(historyData.getSaldo());
        ArrayList<SingleTransferDetails> transferDetails = new ArrayList<>();
        for (TransferDataBean t : historyData.getTransferData()) {
            transferDetails.add(convert(t));
        }
        data.setTranferDerails(transferDetails);
        return data;
    }
    
    private SingleTransferDetails convert(TransferDataBean transfer) {
        SingleTransferDetails details = new SingleTransferDetails();
        details.setAccountNumber(accountNumberService.formatAccountNumber(transfer.getAccountNumber()));
        details.setAmount(transfer.getAmmount());
        details.setDate(transfer.getDate());
        details.setRecipientName(transfer.getRecipient());
        details.setSaldo(transfer.getSaldo());
        details.setTitle(transfer.getTitle());
        details.setDebit(transfer.isDebit());
        return details;
    }
    
    private String createSummaryFileName(TransferSummaryData data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "zestawienie_operacji_od_" + sdf.format(data.getBegin()) + "_do_" + sdf.format(data.getEnd());
    }
    
    private List<TransferDataBean> getUserTransfers(final Date from, final Date to, String accountNumber) {
        // LISTA MUSI BYĆ POSORTOWANA (ROSNĄCO) WZGLĘDEM DATY
        List<TransferDataBean> transfers = new ArrayList<>();
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(from);
        Random rnd = new Random();
        double saldo = rnd.nextDouble() * 2000 + 500;
        
        while (date.getTime().before(to)) {
            boolean debit = rnd.nextBoolean();
            double amount = rnd.nextDouble() * 500 + 10;
            saldo = saldo + (debit ? -1 : 1) * amount;
            TransferDataBean transferData = new TransferDataBean();
            transferData.setAccountNumber("00 0000 0000 0000 0000 0000 0000");
            transferData.setAmmount(amount);
            transferData.setDate(date.getTime());
            transferData.setDebit(debit);
            transferData.setRecipient("Osbiorca " + rnd.nextInt(1000));
            transferData.setSaldo(saldo);
            transferData.setTitle("Tytuł " + rnd.nextInt(1000));
            transfers.add(transferData);
            date.add(Calendar.DAY_OF_MONTH, 2);
        }
        
        return transfers != null ? transfers : Collections.emptyList();
    }
    
    private double getSaldo(String accountNumber) {
        Optional<UserData> userData = loginUserUtil.getLoggedInUser();
        
        if (userData.isPresent()) {
            return userService.getSaldo(userData.get(), accountNumber);
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }

    /**
     * Tworzy wykres ze stanem salda od czasu.
     * 
     * @param transfers przelewy.
     * @return wykres.
     */
    private LineChartModel createSaldoChart(final List<TransferDataBean> transfers, final Date begin, final Date end) {
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        SimpleDateFormat ddMmYyyyFormat = new SimpleDateFormat("yyyy-MM-dd");
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        
        if (!transfers.isEmpty()) {
            for (TransferDataBean t : transfers) {
                double value = Precision.round(t.getSaldo(), 2);
                series.set(ddMmYyyyFormat.format(t.getDate()), value);
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
            }
        }
        else {
            min = 100;
            max = 900;
        }
        
        model.addSeries(series);
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Saldo");
        yAxis.setMin((int) (min - 100.0));
        yAxis.setMax((int) (max + 100.0));
        
        DateAxis axis = new DateAxis("Data");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        axis.setMin(ddMmYyyyFormat.format(begin));
        axis.setMax(ddMmYyyyFormat.format(end));
        model.getAxes().put(AxisType.X, axis);
        return model;
    }

    public void setPdfService(IPdfGeneratorService pdfService) {
        this.pdfService = pdfService;
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setLoginUserUtil(SpringSecurityContextUtil loginUserUtil) {
        this.loginUserUtil = loginUserUtil;
    }
}
