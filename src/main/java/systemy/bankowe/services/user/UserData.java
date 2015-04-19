package systemy.bankowe.services.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import systemy.bankowe.dto.RoleDto;
import systemy.bankowe.dto.UserDto;

/**
 * Informacje na tema zalogowanego użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class UserData implements UserDetails {
    /**
     * UID.
     */
    private static final long serialVersionUID = -4507510932062523271L;
    /**
     * Maksymalna liczba prób do zalogowania.
     */
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    /**
     * Prefiks roli użytkownika.
     */
    private static final String ROLE_PREFIX = "ROLE_";
    /**
     * Dane użytkownika.
     */
    private UserDto userDto;
    /**
     * Lista uprawnień użytkownika.
     */
    private List<GrantedAuthority> authorities;
    
    
    /**
     * Konstruktor.
     * 
     * @param userDto dane użytkownika.
     */
    public UserData(final UserDto userDto) {
        this.userDto = userDto;
        authorities = new ArrayList<>();
        for (RoleDto r : userDto.getRoles()) {
            authorities.add(new Authority(ROLE_PREFIX + r.getName()));
        }
    }
    
    /**
     * Zwraca imię użytkownika.
     * 
     * @return imię użytkownika.
     */
    public String getName() {
        return userDto.getName();
    }

    /**
     * Zwraca nazwisko użytkownika.
     * 
     * @return nazwisko użytkownika.
     */
    public String getSurname() {
        return userDto.getSurname();
    }
    
    /**
     * Zwraca imię i nazwisko użytkownika.
     * 
     * @return imię i nazwisko użytkownika.
     */
    public String getNameAndSurname() {
        return userDto.getName() + " " + userDto.getSurname();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return userDto.getLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return userDto.getFailAttempts() < MAX_LOGIN_ATTEMPTS;
    }
    
    /**
     * Zwraca pozostałą liczbę prób logowania.
     * 
     * @return liczba pozostałych prób do zalogowania.
     */
    public int getRemainingLoginAttempts() {
        int remainingAttempts = MAX_LOGIN_ATTEMPTS - userDto.getFailAttempts();
        return remainingAttempts < 0 ? 0 : remainingAttempts;
    }
    
    /**
     * Zwraca DTO usera.
     * 
     * @return DTO usera.
     */
    UserDto getUserDto() {
        return userDto;
    }
}
