package systemy.bankowe.annotations;

import org.slf4j.Logger;

/**
 * Producent loggerów.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class CustomLoggerFactory implements LoggerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(final Class<?> clazz) {
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }
}