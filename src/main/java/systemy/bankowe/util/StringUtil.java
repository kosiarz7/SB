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
    
    /**
     * Zamienia napis skladajacy sie z cyfr na tablice
     * 
     * @param s napis składający się z cyfr
     * @return tablica cyfr
     * @throws IllegalArgumentException gdy w napisie sa inne znaki niz cyfry
     */
    public static int[] toIntArray(String s) {
    	
    	int[] intArray = new int[s.length()];
    	
        for(int i=0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
              throw new IllegalArgumentException();
            }
            intArray[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
          }
        
        return intArray;
    }
    
    /**
     * Zamienia tablice liczb na napis
     * 
     * @param digits tablica liczb
     * @return napis - liczba
     */
    public static String toString(int[] digits) {
    	
    	StringBuffer sb = new StringBuffer();
    	
    	for (int i : digits) {
			sb.append(Integer.toString(i));
		}
    	
    	return sb.toString();
    	
    }
}
