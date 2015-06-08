package systemy.bankowe.flows.definedrecipients;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import systemy.bankowe.dto.DefinedRecipientDto;

public class DefinedRecipientsDataBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -8841691150098828778L;
    /**
     * Numer konta.
     */
    private String accountNumber;
    /**
     * Nazwa.
     */
    @Size(min = 3, max = 512)
    private String name;
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
     * Wybrany odbiorca.
     */
    private DefinedRecipientDto selectedDto;
    /**
     * Czy formatka ma być w trybie edycji?
     */
    private boolean editMode = false;
    /**
     * Lista zdefiniowanych odbiorców.
     */
    private List<DefinedRecipientDto> defindedRecipients;
    
    private String message;

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

    public List<DefinedRecipientDto> getDefindedRecipients() {
        return defindedRecipients;
    }

    public void setDefindedRecipients(List<DefinedRecipientDto> defindedRecipients) {
        this.defindedRecipients = defindedRecipients;
    }

    public DefinedRecipientDto getSelectedDto() {
        return selectedDto;
    }

    public void setSelectedDto(DefinedRecipientDto selectedDto) {
        this.selectedDto = selectedDto;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
