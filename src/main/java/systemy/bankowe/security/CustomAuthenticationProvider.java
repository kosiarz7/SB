package systemy.bankowe.security;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.services.encrypt.EncryptException;
import systemy.bankowe.services.encrypt.IEncryptor;
import systemy.bankowe.services.user.IUserService;
import systemy.bankowe.services.user.UserData;
import systemy.bankowe.util.CollectionsUtil;

/**
 * Uwierzytelnianie użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    /**
     * Koder.
     */
    private IEncryptor encryptor;
    /**
     * Serwis dla użytkownika.
     */
    private IUserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        UserData user = userService.loadUserByLogin(authentication.getName());
        checkPassword((String) authentication.getCredentials(), user);
        LOGGER.info(
                "authenticate|Do systemu zalogował się użytkownik: {} o uprawnieniach: {}",
                user.getUsername(),
                CollectionsUtil.toString(user.getAuthorities().stream().map(a -> a.getAuthority())
                        .collect(Collectors.toList())));
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }
    
    /**
     * Sprawdza czy przesłane hasło ma skrót taki sam jak przesłany użytkownik.
     * 
     * @param passowrd hasło.
     * @param userData dane użytkownika.
     * @throws BadCredentialsException gdy przesłane hasło ma inny skrót od zadanego.
     */
    protected void checkPassword(final String passowrd, final UserData userData) throws BadCredentialsException {
        String currentHash = null;
        
        try {
            currentHash = encryptor.encrypt(passowrd);
        }
        catch (EncryptException e) {
            LOGGER.error("checkPassword|Wystąpił błąd podczas obliczania skrótu hasła.", e);
            throw new BadCredentialsException("Wystąpił błąd podczas próby autoryzacji.", e);
        }
        
        if (!currentHash.equalsIgnoreCase(userData.getPassword())) {
            LOGGER.info("checkPassword|Nieudana próba logowania dla użytkownika: {}", userData.getUsername());
            throw new CustomBadCredentialsException("Podane hasło jest nieprawidłowe.", userData.getUsername());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Class<?> arg0) {
        return true;
    }

    /**
     * Ustawia koder napisów.
     * 
     * @param encryptor koder.
     */
    public void setEncryptor(final IEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    /**
     * Ustawia serwis użytkownika.
     * 
     * @param userService serwis użytkownika.
     */
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}