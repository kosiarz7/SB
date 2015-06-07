package systemy.bankowe.services.pdf;

/**
 * Wyjątek oznacza, że wystąpił błąd podczas generowania pdfa.
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class PdfGenerationException extends Exception {

    private static final long serialVersionUID = -4354919026777962607L;

    public PdfGenerationException() {
        super();
    }
    
    public PdfGenerationException(String msg) {
        super(msg);
    }
    
    public PdfGenerationException(Throwable e) {
        super(e);
    }
}
