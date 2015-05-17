package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;
import java.util.List;

/**
 * Szczegóły rachunku.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class AccountDetails implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -3880441541851750825L;
    /**
     * Nazwa.
     */
    private String name;
    /**
     * Numer.
     */
    private String number;
    /**
     * Kwota.
     */
    private double amount;
    /**
     * Włąścieciele rachunku.
     */
    private List<AccountOwnerDetails> owners;
    
    public AccountDetails amount(double amount) {
        this.amount = amount;
        return this;
    }
    
    public AccountDetails name(String name) {
        this.name = name;
        return this;
    }
    
    public AccountDetails number(String number) {
        this.number = number;
        return this;
    }
    
    public AccountDetails owners(List<AccountOwnerDetails> owners) {
        this.owners = owners;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<AccountOwnerDetails> getOwners() {
        return owners;
    }

    public void setOwners(List<AccountOwnerDetails> owners) {
        this.owners = owners;
    }
}
