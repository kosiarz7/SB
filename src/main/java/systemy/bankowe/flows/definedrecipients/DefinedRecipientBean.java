package systemy.bankowe.flows.definedrecipients;

import java.io.Serializable;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dto.DefinedRecipientDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.accountnumber.IAccountNumberService;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.util.StringUtil;

public class DefinedRecipientBean implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -829694990955208898L;
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefinedRecipientBean.class);
    
    private IAccountNumberService accountNumberService;
    
    private CommonDao<DefinedRecipientDto> commonDao;
    
    private SpringSecurityContextUtil userUtil;
    
    public void init(final DefinedRecipientsDataBean data) {
        data.setDefindedRecipients(userUtil.getAllDefinedRecipients());
    }
    
    public boolean validateRecipientData(final DefinedRecipientsDataBean data) {
        if (!validateAccountNumber(data.getAccountNumber())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny numer konta.",
                            "Niepoprawny numer konta."));
            return false;
        }
        
        return true;
    }
    
    private boolean validateAccountNumber(String accountNumber) {
        String account = StringUtil.removeWhitespaces(accountNumber);
        return accountNumberService.isValid(account);
    }
    
    public void addNewDefindedRecipient(final DefinedRecipientsDataBean data) {
        try {
            commonDao.save(createDto(data));
            data.setMessage("Odbiorca został dodany.");
        }
        catch (Exception e) {
            LOGGER.error("addNewDefindedRecipient|Wystąpił błąd podczas próby dodania nowego odbiorcy.", e);
            data.setMessage("Wystąpił bład podczas dodawania nowego odbiorcy. Spróbuj później.");
        }
    }
    
    private DefinedRecipientDto createDto(final DefinedRecipientsDataBean data) {
        DefinedRecipientDto defindeRecipientDto = new DefinedRecipientDto();
        fillFields(data, defindeRecipientDto);
        return defindeRecipientDto;
    }
    
    private void fillFields(final DefinedRecipientsDataBean data, final DefinedRecipientDto defindeRecipientDto) {
        defindeRecipientDto.setAccountNumber(accountNumberService.removeWhitespaces(data.getAccountNumber()));
        defindeRecipientDto.setName(data.getName());
        defindeRecipientDto.setCity(data.getCity());
        defindeRecipientDto.setStreet(data.getStreet());
        defindeRecipientDto.setStreetNo(data.getStreetNo());
        defindeRecipientDto.setZipcode(data.getZipcode());
        defindeRecipientDto.setUser(getCurrentUserDto());
    }
    
    private UserDto getCurrentUserDto() {
        Optional<UserData> user = userUtil.getLoggedInUser();
        if (user.isPresent()) {
            return user.get().getUserDto();
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }
    
    public void copyDataToEdit(final DefinedRecipientsDataBean data) {
        DefinedRecipientDto dto = data.getSelectedDto();
        data.setAccountNumber(accountNumberService.formatAccountNumber(dto.getAccountNumber()));
        data.setCity(dto.getCity());
        data.setName(dto.getName());
        data.setStreet(dto.getStreet());
        data.setStreetNo(dto.getStreetNo());
        data.setZipcode(dto.getZipcode());
    }
    
    public void saveChanges(final DefinedRecipientsDataBean data) {
        try {
            DefinedRecipientDto dto = data.getSelectedDto();
            fillFields(data, dto);
            commonDao.update(dto);
            data.setMessage("Zmiany zostały zapisane.");
        }
        catch (Exception e) {
            LOGGER.error("saveChanges|Wystąpił błąd podczas próby dodania nowego odbiorcy.", e);
            data.setMessage("Wystąpił bład podczas próby zapisania zmian. Spróbuj później.");
        }
    }
    
    public void delete(final DefinedRecipientsDataBean data) {
        try {
            commonDao.delete(data.getSelectedDto());
            data.setMessage("Odbiorca został usunięty.");
        }
        catch (Exception e) {
            LOGGER.error("delete|Wystąpił błąd podczas próby dodania nowego odbiorcy.", e);
            data.setMessage("Wystąpił bład podczas usunięcia odbiorcy. Spróbuj później.");
        }
    }

    public void setAccountNumberService(IAccountNumberService accountNumberService) {
        this.accountNumberService = accountNumberService;
    }

    public void setCommonDao(CommonDao<DefinedRecipientDto> commonDao) {
        this.commonDao = commonDao;
    }

    public void setUserUtil(SpringSecurityContextUtil userUtil) {
        this.userUtil = userUtil;
    }
}
