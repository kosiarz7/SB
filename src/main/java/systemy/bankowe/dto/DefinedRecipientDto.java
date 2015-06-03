package systemy.bankowe.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Encja reprezentuje zdefiniowanego odbiorcę.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "DEFINDED_RECIPIENTS")
public class DefinedRecipientDto implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEFINDED_RECIPIENTS_SEQ")
    @SequenceGenerator(name = "DEFINDED_RECIPIENTS_SEQ", sequenceName = "DEFINDED_RECIPIENTS_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private int id;
    /**
     * Użytkownik, dla którego jest zdefiniowany odbiorca.
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserDto user;
    /**
     * Numer konta.
     */
    @Column(name = "ACCOUNT_NUMBER", nullable = false, length = 26)
    private String accountNumber;
    /**
     * Nazwa.
     */
    @Column(name = "NAME", nullable = false, length = 512)
    private String name;
    /**
     * Ulica.
     */
    @Column(name = "STREET", nullable = false, length = 100)
    private String street;
    /**
     * Numer ulicy.
     */
    @Column(name = "STREET_NO", nullable = false, length = 30)
    private String streetNo;
    /**
     * Kod pocztowy.
     */
    @Column(name = "ZIPCODE", nullable = false, length = 6)
    private String zipcode;
    /**
     * Miasto.
     */
    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    // SETTERY I GETTERY
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
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
}
