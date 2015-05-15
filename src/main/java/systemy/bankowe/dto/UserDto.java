package systemy.bankowe.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Encja użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
@Entity
@Table(name = "klienci")
public class UserDto extends AbstractDto implements Serializable {
    /**
     * UID.
     */
    private static final long serialVersionUID = -9122458827485225637L;
    /**
     * Id użytkownika.
     */
    @Id
    @Column(name = "id_klient", nullable = false)
    private int id;
    /**
     * Imię użytkownika.
     */
    @Column(name = "imie", nullable = false, length = 64)
    private String name;
    /**
     * Nazwisko użytkownika.
     */
    @Column(name = "nazwisko", nullable = false, length = 64)
    private String surname;
    /**
     * Login użytkownika.
     */
    @Column(name = "login", nullable = false, length = 100)
    private String login;
    /**
     * Skrót hasła użytkownika.
     */
    @Column(name = "haslo", nullable = false, length = 128)
    private String password;
    /**
     * Liczba nieudanych prób logowania.
     */
    @Column(name = "nieudane_logowania", nullable = false)
    private int failAttempts;
    /**
     * Adres.
     */
    @Column(name = "adres", nullable = false, length = 64)
    private String address;
    /**
     * Adres.
     */
    @Column(name = "kod_pocztowy", nullable = false, length = 6)
    private String zipcode;
    /**
     * Adres.
     */
    @Column(name = "miasto", nullable = false, length = 32)
    private String city;
    /**
     * Pesel.
     */
    @Column(name = "pesel", nullable = false, length = 32)
    private String pesel;
    /**
     * Nr dowodu osobistego.
     */
    @Column(name = "nr_dowodu", nullable = false, length = 32)
    private String idCardNumber;
    /**
     * Mail.
     */
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    /**
     * Uprawnienia użytkownika.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uprawnienia_klientow", joinColumns = { @JoinColumn(name = "klient_id", referencedColumnName = "id_klient") }, inverseJoinColumns = { @JoinColumn(name = "uprawnienie_id", referencedColumnName = "id") })
    private Set<RoleDto> roles;
    /**
     * Obywatelstwo.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obywatelstwa")
    private CitizenshipDto citizenship;
    /**
     * Konta użytkownika.
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "klienci_rachunki", joinColumns = { @JoinColumn(name = "id_klient", referencedColumnName = "id_klient") }, inverseJoinColumns = { @JoinColumn(name = "id_rachunek", referencedColumnName = "id_rachunek") })
    private List<AccountDto> accounts;

    /**
     * Konstrutkor.
     */
    public UserDto() {
        id = UNLOADED_ID;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public int getFailAttempts() {
        return failAttempts;
    }

    public void setFailAttempts(int failAttempts) {
        this.failAttempts = failAttempts;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CitizenshipDto getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(CitizenshipDto citizenship) {
        this.citizenship = citizenship;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
}