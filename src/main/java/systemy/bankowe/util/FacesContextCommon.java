package systemy.bankowe.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesContextCommon {
    private FacesContextCommon() {
    }
    
    public static final String INFO = "INFO";
    public static final String WARNING = "WARNING";
    public static final String ERROR = "ERROR";
    public static final String FATAL = "FATAL";
    
    public static String toString(Severity level) {
        int ordinal = level.getOrdinal();
        
        if (FacesMessage.SEVERITY_INFO.getOrdinal() == ordinal) {
            return INFO;
        }
        else if (FacesMessage.SEVERITY_WARN.getOrdinal() == ordinal) {
            return WARNING;
        }
        else if (FacesMessage.SEVERITY_ERROR.getOrdinal() == ordinal) {
            return ERROR;
        }
        else if (FacesMessage.SEVERITY_FATAL.getOrdinal() == ordinal) {
            return FATAL;
        }
        else {
            return INFO;
        }
    }
    
    public static Severity toSeverity(String level) {
        switch (level) {
            case INFO:
                return FacesMessage.SEVERITY_INFO;
            case WARNING:
                return FacesMessage.SEVERITY_WARN;
            case ERROR:
                return FacesMessage.SEVERITY_ERROR;
            case FATAL:
                return FacesMessage.SEVERITY_FATAL;
            default:
                return FacesMessage.SEVERITY_INFO;
        }
    }
    
    public static void addMessage(String id, Severity level, String msg, String details) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(level, msg, details));
    }
    
    public static void addMessage(Severity level, String msg, String details) {
        addMessage(null, level, msg, details);
    }
    
    public static void addMessage(Severity level, String msg) {
        addMessage(null, level, msg, "");
    }
}
