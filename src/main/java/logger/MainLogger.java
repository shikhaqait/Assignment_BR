package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc

/**
 * The Class MainLogger.
 */
public class MainLogger {

    /**
     * The log.
     */
    private Logger log;

    /**
     * Instantiates a new main logger.
     *
     * @param claz the claz
     */
    public MainLogger(@SuppressWarnings("rawtypes") Class claz) {
        log = LogManager.getLogger(claz);
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return log;
    }

    /**
     * Logger.
     *
     * @return the logger
     */
    public static Logger logger() {
        Logger log = LogManager.getLogger("console");
        return log;
    }

}
