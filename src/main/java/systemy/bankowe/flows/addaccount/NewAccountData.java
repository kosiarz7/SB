package systemy.bankowe.flows.addaccount;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import systemy.bankowe.dto.CitizenshipDto;

/**
 * Dane dla nowego konta bankowego.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class NewAccountData implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -8686388394216395913L;
    /**
     * Imię użytkownika.
     */
    @Size(min = 3, max = 50)
    private String name;
    /**
     * Nazwisko użytkownika.
     */
    @Size(min = 3, max = 100)
    private String surname;
    /**
     * Hasło.
     */
    @Size(min = 5)
    private String password;
    /**
     * Potwierdzenie hasła.
     */
    @Size(min = 5)
    private String confirmedPassword;
    /**
     * Mail.
     */
    @Email
    private String mail;
    /**
     * Potwierdzenie maila.
     */
    @Email
    private String confirmedMail;
    /**
     * Ulica.
     */
    @Size(min = 3, max = 100)
    private String street;
    /**
     * Numer domu/mieszkania.
     */
    @Size(min = 1, max = 30)
    private String streetNo;
    /**
     * Kod pocztowy.
     */
    @Pattern(regexp = "\\d\\d-\\d\\d\\d", message = "Kod pocztowy musi być w formacie: DD-DDD")
    private String zipcode;
    /**
     * Miasto
     */
    @Size(min = 3, max = 100)
    private String city;
    /**
     * Nazwa konta bankowego.
     */
    @Size(min = 3, max = 255)
    private String accountName;
    /**
     * Pesel.
     */
    @Size(min = 11, max = 11)
    private String pesel;
    /**
     * Numere dowodu osobistego.
     */
    @Size(min = 9, max = 9)
    private String idCardNo;
    /**
     * Obywatelstwo.
     */
    private String citizenship;
    /**
     * Dostępne obywatelstwa.
     */
    private List<CitizenshipDto> citizenshipsDto;
    /**
     * Lista obywatelstw.
     */
    private List<String> citizenships;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getConfirmedMail() {
        return confirmedMail;
    }

    public void setConfirmedMail(String confirmedMail) {
        this.confirmedMail = confirmedMail;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public List<CitizenshipDto> getCitizenshipsDto() {
        return citizenshipsDto;
    }

    public void setCitizenshipsDto(List<CitizenshipDto> citizenshipsDto) {
        this.citizenshipsDto = citizenshipsDto;
    }

    public List<String> getCitizenships() {
        return citizenships;
    }

    public void setCitizenships(List<String> citizenships) {
        this.citizenships = citizenships;
    }

}