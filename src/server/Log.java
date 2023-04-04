package server;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;

public class Log {
    private static final FileHandler fileHandler;
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    static {
        try {
            fileHandler = new FileHandler("logs.log");
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger(){
        return logger;
    }
}
