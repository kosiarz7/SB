package systemy.bankowe.util;

import java.util.Collection;

import com.google.common.base.Joiner;

/**
 * Wspólne metody dla kolekcji.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CollectionsUtil {
    /**
     * Konwertuje kolekcję na łańcuch składający się z elementów kolekcji oddzielonych spacją.
     * 
     * @param collection kolekcja obiektów.
     * @return łańcuch stworzony na podstawie kolekcji.
     */
    public static String toString(final Collection<?> collection) {
        return toString(collection, " ");
    }

    /**
     * Konwertuje kolekcję na łańcuch składający się z elementów kolekcji oddzielonych przesłanym separatorem.
     * 
     * @param collection kolekcja obiektów.
     * @param separator separator.
     * @return łańcuch stworzony na podstawie kolekcji.
     */
    public static String toString(final Collection<?> collection, final String separator) {
        return Joiner.on(separator).join(collection.iterator());
    }
}