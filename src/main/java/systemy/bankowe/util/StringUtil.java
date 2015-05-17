package systemy.bankowe.util;


/**
 * Wspólne metody dla łańcuchów.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
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

    /**
     * Usuwa białe znaki z przesłanego napisu.
     * 
     * @param string napis.
     * @return napis bez białych znaków.
     */
    public static String removeWhitespaces(String string) {
        String removedWhitespaces = "";

        if (!StringUtil.isEmpty(string)) {
            removedWhitespaces = string.trim();
            removedWhitespaces = removedWhitespaces.replaceAll(" ", "");
            removedWhitespaces = removedWhitespaces.replaceAll("\t", "");
        }

        return removedWhitespaces;
    }
}
