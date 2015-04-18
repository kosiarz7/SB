package systemy.bankowe.services.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;
import systemy.bankowe.util.StringUtil;

/**
 * Kodowanie tekstu za pomocą algorytmu SHA-512.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class SHA512Encryptor implements IEncryptor {
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String encrypt(final String plainText) throws EncryptException {
        if (StringUtil.isEmpty(plainText)) {
            LOGGER.error("encrypt|Do metody został przesłany pusty tekst.");
            throw new IllegalArgumentException("Przesłany tekst nie może być pusty.");
        }
        String encodedPassword = "";
        
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            byte [] digesta = mda.digest(plainText.getBytes());
            encodedPassword = String.valueOf(Hex.encodeHex(digesta));
            encodedPassword = encodedPassword.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            LOGGER.error("encrypt|Nie odnaleziono algorytmu SHA-512.", e);
            throw new EncryptException(e);
        }
        
        return encodedPassword;
    }
}
