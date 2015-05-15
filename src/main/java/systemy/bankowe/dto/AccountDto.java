package systemy.bankowe.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Encja konta bankowego.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "rachunki")
public class AccountDto implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 8107890130209276332L;
    /**
     * Numer rachunku.
     */
    @Id
    @Column(name = "id_rachunek", nullable = false, length = 26)
    private String number;
    /**
     * Nazwa rachunku.
     */
    @Column(name = "nazwa", nullable = false, length = 255)
    private String name;
    /**
     * Saldo.
     */
    @Column(name = "saldo")
    private double saldo;
    /**
     * Data założenia.
     */
    @Column(name = "data_zalozenia")
    private Date setupDate;

    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    // GETTERY I SETTERY
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }
}
