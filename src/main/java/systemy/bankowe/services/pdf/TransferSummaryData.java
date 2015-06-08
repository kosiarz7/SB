package systemy.bankowe.services.pdf;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Dane potrzebne do wygenerowania pdfa z zestawieniem przelewów.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class TransferSummaryData implements Serializable {

    private static final long serialVersionUID = -3192686617378084832L;
    /**
     * Suma uznań.
     */
    private double credits;
    /**
     * Suma obciążeń.
     */
    private double debits;
    /**
     * Początkowy stan konta.
     */
    private double beginAmount;
    /**
     * Końcowy stan konta.
     */
    private double endAmount;
    /**
     * Saldo.
     */
    private double saldo;
    /**
     * Data początku zestawienia.
     */
    private Date begin;
    /**
     * Data końca zestawienia.
     */
    private Date end;
    /**
     * Szczegóły kolejnych przlewów.
     */
    private List<SingleTransferDetails> tranferDerails;

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

    public double getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(double beginAmount) {
        this.beginAmount = beginAmount;
    }

    public double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(double endAmount) {
        this.endAmount = endAmount;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<SingleTransferDetails> getTranferDerails() {
        return tranferDerails;
    }

    public void setTranferDerails(List<SingleTransferDetails> tranferDerails) {
        this.tranferDerails = tranferDerails;
    }
}
