package systemy.bankowe.services.encrypt;

/**
 * Interfejs obiektu kodującego tekst.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IEncryptor {
    /**
     * Koduje przesłany tekst.
     * 
     * @param plainText tekst w postaci jawnej.
     * @return zakodowany tekst.
     * @throws EncryptException gdy wystąpiły błędy podczas kodowania.
     */
    String encrypt(final String plainText) throws EncryptException;
}
