
package krislyn.logger;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;


public class WriteLogToFile {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(WriteLogToFile.class.getName());
        FileHandler fileHandler = new FileHandler("app.log", true);        
        logger.addHandler(fileHandler);

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Program start");
        }
        logger.info("Inserted something");
        logger.info("Clicked button");
    }
    
}