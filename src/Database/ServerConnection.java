package Database;

import server.Log;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ServerConnection {

    private volatile static ServerConnection INSTANCE;
    private static final String filePath = "./serverSettings.cfg";
    private static Connection connection;
    private ServerConnection() {
        try {
            FileInputStream file =  new FileInputStream(filePath);
            Properties config = new Properties();
            config.load(file);
            connection = DriverManager.getConnection((String)config.get("url"), (String)config.get("user"), (String)config.get("password"));
        } catch (Exception e) {
            Log.getLogger().warning("Check server .cfg file");
            Log.getLogger().warning(e.getMessage());
            throw new RuntimeException("Couldn't initialize database connection");
        }
    }

    public static Connection getINSTANCE(){
        if(INSTANCE == null){
            synchronized (ServerConnection.class){
                if (INSTANCE == null){
                    INSTANCE = new ServerConnection();
                }
            }
        }
        try {
            if (!connection.isClosed()) {
                return connection;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        INSTANCE = new ServerConnection();
        return connection;
    }

}
