package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;

public class AccountStub implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Saldo.
     */
    private double saldo;
    /**
     * Numer konta.
     */
    private String number;
    /**
     * Nazwa konta.
     */
    private String name;
    /**
     * Czy konto posiada wspówłaścicieli?
     */
    private boolean coowners;

    
    public AccountStub saldo(double saldo) {
        this.saldo = saldo;
        return this;
    }
    
    public AccountStub number(String number) {
        this.number = number;
        return this;
    }

    public AccountStub name(String name) {
        this.name = name;
        return this;
    }
    
    public AccountStub coowners(boolean coowners) {
        this.coowners = coowners;
        return this;
    }
    
    // SETTERY I GETTERY
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCoowners() {
        return coowners;
    }

    public void setCoowners(boolean coowners) {
        this.coowners = coowners;
    }
}
