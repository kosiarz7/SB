package systemy.bankowe.flows.addnextaccount;

import java.io.Serializable;

import javax.faces.application.FacesMessage.Severity;

import systemy.bankowe.util.FacesContextCommon;

/**
 * Dane kolejnego konta.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
public class NextAccount implements Serializable {

    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Nazwa konta.
     */
    private String name;

    private String message;

    private Severity level;

    public String getLevelAsString() {
        return FacesContextCommon.toString(level);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Severity getLevel() {
        return level;
    }

    public void setLevel(Severity level) {
        this.level = level;
    }
}