package systemy.bankowe.services.encrypt;

/**
 * Wyjątek oznaczający wystąpienie błędu podczas kodowania tekstu.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class EncryptException extends Exception {

    /**
     * UID.
     */
    private static final long serialVersionUID = 568998053191194369L;
    
    /**
     * Konstrukotr.
     */
    public EncryptException() {
        super();
    }
    
    /**
     * Konstruktor.
     * 
     * @param msg komunikat błędu.
     */
    public EncryptException(final String msg) {
        super(msg);
    }
    
    /**
     * Konstruktor.
     * 
     * @param cause przyczyna wyjątku.
     */
    public EncryptException(final Throwable cause) {
        super(cause);
    }
}