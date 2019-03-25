package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WithLog4j {

    static final Logger logger = LogManager.getLogger(Logger.class.getName());

    public static void main(String[] args) {    
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }

}
