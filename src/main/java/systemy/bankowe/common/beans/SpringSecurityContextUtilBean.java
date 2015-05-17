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
                user = Optional.of((UserData) principal);
            }
        }

        return user;
    }
}