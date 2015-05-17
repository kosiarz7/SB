package systemy.bankowe.services.mail;

/**
 * Mail do wysłania.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class Mail {
    /**
     * Tytuł wiadomości.
     */
    private String title;
    /**
     * Zawartość wiadomości.
     */
    private String content;
    /**
     * Odbiorca wiadomości.
     */
    private String recipient;
    
    
    /**
     * Zwraca tytuł wiadomości.
     * 
     * @return tytuł wiadomości.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Ustawia tytuł wiadomości.
     * 
     * @param title tytuł wiadomości.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Zwraca zawartość wiadomości.
     * 
     * @return zawartość wiadomości.
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Ustawia zawartość wiadomości.
     * 
     * @param content zawartość wiaodmości.
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Zwraca adres odbiorcy.
     * 
     * @return adres odbiorcy.
     */
    public String getRecipient() {
        return recipient;
    }
    
    /**
     * Ustawia adres odbiorcy.
     * 
     * @param recipient adres odbiorcy.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}