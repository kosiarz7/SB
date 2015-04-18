package systemy.bankowe.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Obiekt wstrzykujący loggery do oznaczonych pól adnotacją @InjectLogger w beanach. 
 * 
 * @author Adam Kopaczewski
 *
 * Copyright © 2015 Adam Kopaczewski
 */
public class LoggerPostProcessor implements BeanPostProcessor {
    /**
     * Logger.
     */
    private final Logger logger;;
    /**
     * Producent loggerów.
     */
    private LoggerFactory loggerFactory;

    
    /**
     * Konstruktor.
     * 
     * @param loggerFactory producent loggerów.
     */
    public LoggerPostProcessor(final LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
        logger = this.loggerFactory.getLogger(LoggerPostProcessor.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        
        for (Field field : fields) {
            if (hasInjectLoggerAnnotation(field.getAnnotations())) {
                injectLogger(field, bean);
            }
        }
        
        return bean;
    }
    
    /**
     * Metoda sprawedza czy wśród tablicy adnotacji znajduje się adnotacja InjectLogger.
     * 
     * @param annotations tablica adnotacji.
     * @return true - tablica zawiera adnotację InjectLogger; false - tablica nie zawiera adnotacji InjectLogger.
     */
    private boolean hasInjectLoggerAnnotation(final Annotation[] annotations) {
        boolean loggable = false;
        
        for (Annotation a : annotations) {
            if (a instanceof InjectLogger) {
                loggable = true;
            }
        }
        
        return loggable;
    }
    
    /**
     * Pole, w które ma zostać wstrzyknięty logger. 
     * 
     * @param field pole, w które ma zostać wstrzyknięty logger.
     * @param bean  przetwarzany spring bean.
     */
    private void injectLogger(final Field field, final Object bean) {
        try {
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, loggerFactory.getLogger(bean.getClass()));
            logger.info("injectLogger| Wstrzyknięcie loggera do klasy: {}", bean.getClass());
        }
        catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
            logger.error("injectLogger| Błąd podczas próby wstrzyknięcia loggera do klasy: {}", bean.getClass(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }
}