package systemy.bankowe.util;

/**
 * Wspólne metody dla łańcuchów.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class StringUtil {
    /**
     * Sprawdza czy przesłany napis jest pusty (null pointer lub łańcuch zawiera dowolną ilość białych znaków).
     * 
     * @param string napis.
     * @return true - napis jest pusty; false - napis nie jest pusty.
     */
    public static boolean isEmpty(final String string) {
        return null == string || "".equals(string.trim());
    }
}
