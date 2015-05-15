package systemy.bankowe.services.mail;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;

/**
 * Serwis wysyłający maile.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class MailService implements IMailService, Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = -5764114184436692666L;
    /**
     * Logger.
     */
    @InjectLogger
    private static final Logger LOGGER = null;
    /**
     * Nadawca.
     */
    private String sender;
    /**
     * Hasło do skrzynki nadawczej.
     */
    private String password;
    /**
     * Host SMTP.
     */
    private String smtpHost;
    /**
     * Port.
     */
    private String port;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMail(Mail mail) throws MessagingException {
        LOGGER.debug("sendMail|Próba wysłania wiadomości do: {}", mail.getRecipient());
        
        Session session = createSession();
        try {
            MimeMessage msg = createMail(session, mail);
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, sender, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            LOGGER.debug("sendMail|Wiadomość została wysłana.");
        }
        catch (MessagingException e) {
            LOGGER.error("sendMail|Wystąpił błąd podczas wysyłania wiadomości.", e);
            throw e;
        }
    }
    
    
    /**
     * Tworzy nową sesję.
     * 
     * @return nowa sesja.
     */
    private Session createSession() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        return Session.getDefaultInstance(properties);
    }
    
    /**
     * Towrzy maila.
     * 
     * @param session sesja.
     * @param mail wiadomość mail.
     * @return mime messaged.
     * @throws MessagingException gdy wystąpił wyjątek podczas tworzenia wiadomości mime.
     */
    private MimeMessage createMail(Session session, Mail mail) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
        message.setSubject(mail.getTitle());
        message.setText(mail.getContent());
        return message;
    }

    /**
     * Ustawia nadawce.
     * 
     * @param sender nadawca.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Ustawia hasło do konta.
     * 
     * @param password hasło.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Ustawia hosta SMTP.
     * 
     * @param smtpHost host SMTP.
     */
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    /**
     * Ustawia port.
     * 
     * @param port port.
     */
    public void setPort(String port) {
        this.port = port;
    }
}