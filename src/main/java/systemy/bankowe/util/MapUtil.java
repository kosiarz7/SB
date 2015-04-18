package systemy.bankowe.util;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Wspólne metody dla map.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class MapUtil {
    /**
     * Konwertuje mapę na napis postaci oddzielonych separatorem wejść do mapy.
     * 
     * @param map mapa.
     * @param separator separator.
     * @return napis.
     */
    public static String toString(final Map<?, ?> map, final String separator) {
        StringBuilder builder = new StringBuilder();
        builder.append("");
        int length = map.size();
        
        if (1 == length) {
            builder.append(entryToString(map.entrySet().iterator().next()));
        }
        else {
            int position = 0;
            for (Entry<?, ?> e : map.entrySet()) {
                builder.append(entryToString(e));
                if (position++ < length - 1) {
                    builder.append(separator);
                }
            }
        }
        
        return builder.toString();
    }
    
    /**
     * Konwertuje mapę na napis postaci oddzielonych spacją wejść do mapy.
     * 
     * @param map mapa.
     * @return napis.
     */
    public static String toString(final Map<?, ?> map) {
        return toString(map, " ");
    }
    
    /**
     * Konwertuje wejście mapy na napis postaci "(key=entryKey; value=entryValue)".
     * 
     * @param entry wejście do mapy.
     * @return napis.
     */
    private static String entryToString(final Entry<?, ?> entry) {
        return "(key=" + entry.getKey() + "; value=" + entry.getValue() + ")"; 
    }
}