package systemy.bankowe.services.pdf;

import java.io.Serializable;
import java.util.Date;

/**
 * Szeczgóły pojedynczego przelewu.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class SingleTransferDetails implements Serializable {

    private static final long serialVersionUID = -5094792475591220303L;
    /**
     * Nazwa odbiorcy.
     */
    private String recipientName;
    /**
     * Konto odbioercy.
     */
    private String accountNumber;
    /**
     * Tytuł przelewu.
     */
    private String title;
    /**
     * Data przelewu.
     */
    private Date date;
    /**
     * Kwota przelewu.
     */
    private double amount;
    /**
     * Saldo po przelewie.
     */
    private double saldo;
    /**
     * Czy przelew był obciążeniem?
     */
    private boolean debit;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }
}
