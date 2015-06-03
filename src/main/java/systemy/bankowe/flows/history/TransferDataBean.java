package systemy.bankowe.flows.history;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TransferDataBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 4327788725314597831L;
    /**
     * Tytuł.
     */
    private String title;
    /**
     * Numer konta.
     */
    private String accountNumber;
    /**
     * Szczegóły przelewu.
     */
    private String recipient;
    /**
     * Data przelewu.
     */
    private Date date;
    /**
     * Kwota przelewu.
     */
    private double ammount;
    /**
     * Saldo po przelewie.
     */
    private double saldo;
    /**
     * Czy przelew jest uznaniem?
     */
    private boolean debit;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public TransferDataBean title(String title) {
        this.title = title;
        return this;
    }

    public TransferDataBean accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public TransferDataBean recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public TransferDataBean date(Date date) {
        this.date = date;
        return this;
    }

    public TransferDataBean ammount(double ammount) {
        this.ammount = ammount;
        return this;
    }

    public TransferDataBean saldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public TransferDataBean debit(boolean debit) {
        this.debit = debit;
        return this;
    }

    // GETTERY I SETTERY
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
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
