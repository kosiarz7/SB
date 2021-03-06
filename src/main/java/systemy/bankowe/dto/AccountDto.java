package systemy.bankowe.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    @Column(name = "id_rachunek", nullable = false)
    private int id;
    /**
     * Numer rachunku.
     */
    @Column(name = "numer", nullable = false, length = 26)
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
    /**
     * Czy konto jest aktywne?
     */
    @Column(name = "enabled")
    private boolean enabled;
    /**
     * Właściciele konta.
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "accounts")
    private Set<UserDto> owners;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserDto> getOwners() {
        return owners;
    }

    public void setOwners(Set<UserDto> owners) {
        this.owners = owners;
    }
}
