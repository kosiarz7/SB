package systemy.bankowe.services.pdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

class Fonts {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fonts.class);
    private static BaseFont baseFont;
    private static Font normalFont;
    private static Font boldFont;
    private static Font smallFont;
    
    /**
     * Zwraca żądaną czcionkę.
     * 
     * @param fontType typ żądanej czionki.
     * @return żądana czcionka.
     */
    public static Font getFont(final FontTypes fontType) {
        Font font = null;

        switch (fontType) {
            case BOLD:
                if (null == boldFont) {
                    initFonts();
                }
                font = boldFont;
                break;
            case NORMAL:
                if (null == normalFont) {
                    initFonts();
                }
                font = normalFont;
                break;
            case SMALL:
                if (null == smallFont) {
                    initFonts();
                }
                font = smallFont;
                break;
            default:
                throw new IllegalArgumentException("Brak czcionki: " + fontType);
        }

        return font;
    }
    
    /**
     * Inicjalizacja czcionek.
     */
    private static void initFonts() {
        try {
            baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
            normalFont = new Font(baseFont, 12, Font.NORMAL);
            boldFont = new Font(baseFont, 12, Font.BOLD);
            smallFont = new Font(baseFont, 9, Font.NORMAL);
        }
        catch (Exception e) {
            LOGGER.error("static|Wystąpił błąd podczas inicjalziacji czcionek.", e);
            new RuntimeException(e);
        }
    }
}
