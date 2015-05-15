package systemy.bankowe.flows.addaccount;

/**
 * Wyjątek symbolizuje brak żądanej roli.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class LackOfRoleException extends Exception {

    /**
     * UID.
     */
    private static final long serialVersionUID = 6662244360138447886L;
    
    public LackOfRoleException() {
        super();
    }
    
    public LackOfRoleException(String msg) {
        super(msg);
    }
}
