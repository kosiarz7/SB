package systemy.bankowe.services.user;

import org.springframework.security.core.AuthenticationException;

/**
 * Wyjątek informujący o tym, że nie ma takiego użytkownika.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class NoSuchUserException extends AuthenticationException {

    /**
     * UID.
     */
    private static final long serialVersionUID = 8601780131501035458L;

    /**
     * Konstrukotr.
     * 
     * @param msg komunikat błędu.
     */
    public NoSuchUserException(final String msg) {
        super(msg);
    }
}
