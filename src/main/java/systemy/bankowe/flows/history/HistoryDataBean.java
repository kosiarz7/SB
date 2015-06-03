package systemy.bankowe.flows.history;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.primefaces.model.chart.LineChartModel;

public class HistoryDataBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 5908212144430955758L;
    /**
     * Numer rachunku, dla którego została stworzona historia.
     */
    private String currentAccountNumber;
    /**
     * Wykres salda konta.
     */
    private LineChartModel saldoChart;
    /**
     * Data od.
     */
    @Past(message = "Data \"od\" musi być z przeszłości")
    private Date from;
    /**
     * Data do.
     */
    private Date to;
    /**
     * Lista z danymi poszczególnych przelewów.
     */
    private List<TransferDataBean> transferData;
    /**
     * Początkowe saldo.
     */
    private double beginSaldo;
    /**
     * Końcowe saldo.
     */
    private double endSaldo;
    /**
     * Uznania.
     */
    private double credits;
    /**
     * Obciążenia.
     */
    private double debits;
    /**
     * Saldo.
     */
    private double saldo;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public LineChartModel getSaldoChart() {
        return saldoChart;
    }

    public void setSaldoChart(LineChartModel saldoChart) {
        this.saldoChart = saldoChart;
    }

    public List<TransferDataBean> getTransferData() {
        return transferData;
    }

    public void setTransferData(List<TransferDataBean> transferData) {
        this.transferData = transferData;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public double getBeginSaldo() {
        return beginSaldo;
    }

    public void setBeginSaldo(double beginSaldo) {
        this.beginSaldo = beginSaldo;
    }

    public double getEndSaldo() {
        return endSaldo;
    }

    public void setEndSaldo(double endSaldo) {
        this.endSaldo = endSaldo;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getDebits() {
        return debits;
    }

    public void setDebits(double debits) {
        this.debits = debits;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    public void setCurrentAccountNumber(String currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }
}
