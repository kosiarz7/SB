package systemy.bankowe.common.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import systemy.bankowe.dao.CommonDao;
import systemy.bankowe.dao.cyclictransfer.ICyclicTransferDao;
import systemy.bankowe.dao.definedrecipients.IDefindeRecipientDao;
import systemy.bankowe.dto.AccountDto;
import systemy.bankowe.dto.CyclicTransferDto;
import systemy.bankowe.dto.DefinedRecipientDto;
import systemy.bankowe.dto.UserDto;
import systemy.bankowe.security.SpringSecurityContextUtil;
import systemy.bankowe.services.user.UserData;

/**
 * Bean wspomagający pracę ze spring security.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class SpringSecurityContextUtilBean implements Serializable, SpringSecurityContextUtil {

    /**
     * UID.
     */
    private static final long serialVersionUID = -1707242397470945906L;

    private IDefindeRecipientDao definedRecipientDao;

    private ICyclicTransferDao cyclicTransferDao;
    
    private CommonDao<Object> commonDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getLoggedOnUserRolesAsStringList() {
        return getLoggedOnUserRoles().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getLoggedOnUserRoles() {
        return getAuthentication().getAuthorities();
    }

    /**
     * Zwraca dane zalogowanego użytkownika.
     * 
     * @return dane zalogowanego użytkownika.
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggedOnUserNameAndSurname() {
        String nameAndSurename = "";
        Object principal = getAuthentication().getPrincipal();

        if (principal instanceof UserData) {
            nameAndSurename = ((UserData) principal).getNameAndSurname();

        }

        return nameAndSurename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserLoggedIn() {
        Authentication auth = getAuthentication();
        return !(null == auth || auth instanceof AnonymousAuthenticationToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logoutCurrentUser() {
        SecurityContextHolder.clearContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserData> getLoggedInUser() {
        Optional<UserData> user = Optional.empty();
        Authentication auth = getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserData) {
                UserData userData = (UserData) principal;
                UserDto userDto = userData.getUserDto();
                commonDao.refresh(userDto);
                for (AccountDto a : userDto.getAccounts()) {
                    commonDao.refresh(a);
                }
                userDto.setAccounts(userDto.getAccounts().stream().filter(a -> a.isEnabled())
                        .collect(Collectors.toList()));
                user = Optional.of(userData);
            }
        }

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DefinedRecipientDto> getAllDefinedRecipients() {
        Optional<UserData> user = getLoggedInUser();
        if (user.isPresent()) {
            return definedRecipientDao.getDefindeRecipients(user.get().getUserDto());
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CyclicTransferDto> getAllCyclicTransfers() {
        Optional<UserData> user = getLoggedInUser();
        if (user.isPresent()) {
            return cyclicTransferDao.getAllCyclicTransfers(user.get().getUserDto());
        }
        else {
            throw new IllegalStateException("Użytkownik jest niezalogowany!");
        }
    }

    public void setDefinedRecipientDao(IDefindeRecipientDao definedRecipientDao) {
        this.definedRecipientDao = definedRecipientDao;
    }

    public void setCyclicTransferDao(ICyclicTransferDao cyclicTransferDao) {
        this.cyclicTransferDao = cyclicTransferDao;
    }

    public void setCommonDao(CommonDao<Object> commonDao) {
        this.commonDao = commonDao;
    }
}