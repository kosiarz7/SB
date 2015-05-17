package systemy.bankowe.flows.mainwindow;

import java.io.Serializable;

/**
 * Szczegóły właściciela rachunku.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class AccountOwnerDetails implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -6710122233030313441L;
    /**
     * Imię.
     */
    private String name;
    /**
     * Nazwisko.
     */
    private String surename;
    /**
     * Adres.
     */
    private String address;
    /**
     * Kod pocztowy.
     */
    private String zipcode;
    /**
     * Miasto.
     */
    private String city;
    /**
     * Mail.
     */
    private String mail;

    public AccountOwnerDetails name(String name) {
        this.name = name;
        return this;
    }

    public AccountOwnerDetails surename(String surename) {
        this.surename = surename;
        return this;
    }

    public AccountOwnerDetails address(String address) {
        this.address = address;
        return this;
    }

    public AccountOwnerDetails zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public AccountOwnerDetails city(String city) {
        this.city = city;
        return this;
    }

    public AccountOwnerDetails mail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
