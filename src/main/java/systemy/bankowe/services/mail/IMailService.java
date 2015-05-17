package systemy.bankowe.services.mail;

import javax.mail.MessagingException;


/**
 * Serwis obsługujący maile.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public interface IMailService {
    /**
     * Wysyła maila.
     * 
     * @param mail mail.
     * @throws MessagingException gdy wystąpił błąd podczas tworzenia wiadomości.
     */
    void sendMail(final Mail mail) throws MessagingException;
}
