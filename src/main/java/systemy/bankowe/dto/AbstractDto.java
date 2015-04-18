package systemy.bankowe.dto;

/**
 * Bazowa klasa dla wszyskich DTO.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
abstract public class AbstractDto {
    /**
     * Wartość id niezaładowanego obiektu DTO.
     */
    protected static final int UNLOADED_ID = Integer.MIN_VALUE;
    
    /**
     * Zwraca id encji.
     * 
     * @return id encji.
     */
    abstract public int getId();
    
    /**
     * Czy dane z bazy zostały załadowane do DTO?
     * 
     * @return true - tak; false - nie.
     */
    public boolean isLoaded() {
        return getId() != UNLOADED_ID;
    }
}