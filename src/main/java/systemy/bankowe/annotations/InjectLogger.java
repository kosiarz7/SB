package systemy.bankowe.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Adnotacja oznacza pole w beanie, do którego ma zostać wstrzyknięty logger.
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLogger {
}