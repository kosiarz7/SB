package systemy.bankowe.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

import systemy.bankowe.services.user.UserData;

/**
 * Metody wspomagającę pracę z Spring Security.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface SpringSecurityContextUtil {
    /**
     * Zwraca listę (w postaci napisów) uprawnień zalogowanego użytkownika.
     * 
     * @return lista uprawnień zalogowanego użytkownika.
     */
    public List<String> getLoggedOnUserRolesAsStringList();
    /**
     * Zwraca kolekcję uprawnień zalogowanego użytkownika.
     * 
     * @return kolekcja uprawnień zalogowanego użytkownika.
     */
    public Collection<? extends GrantedAuthority> getLoggedOnUserRoles();
    /**
     * Zwraca imię i nazwisko zalogowanego użytkownika.
     *  
     * @return imię i naziwsko.
     */
    String getLoggedOnUserNameAndSurname();
    /**
     * Czy użytkownik jest zalogowany?
     * 
     * @return true - tak; false - nie;
     */
    boolean isUserLoggedIn();
    /**
     * Wlogowuje bieżącego użytkownika.
     */
    void logoutCurrentUser();
    /**
     * Zwraca zalogowanego użytkownika.
     * 
     * @return zalogowany użytkownik.
     */
    Optional<UserData> getLoggedInUser();
}
