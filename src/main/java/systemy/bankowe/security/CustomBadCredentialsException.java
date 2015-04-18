package systemy.bankowe.security;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * Wyjątek występuje gdy użytkownik wpisze złe hasło.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CustomBadCredentialsException extends BadCredentialsException{

    /**
     * UID.
     */
    private static final long serialVersionUID = 6363739123562187336L;
    /**
     * Login użytkownika, który wpisał złe hasło.
     */
    private String login;

    /**
     * Konstruktor.
     * 
     * @param msg wiadomość błędu.
     */
    public CustomBadCredentialsException(final String msg) {
        super(msg);
    }
    
    /**
     * Konstruktor.
     * 
     * @param msg wiadomość błędu.
     * @param login login użytkownika, który wpisał złe hasło.
     */
    public CustomBadCredentialsException(final String msg, final String login) {
        super(msg);
        this.login = login;
    }
    
    /**
     * Zwraca login użytkownika, który wpisał złe hasło.
     * 
     * @return login użytkownika.
     */
    public String getLogin() {
        return login;
    }
}
