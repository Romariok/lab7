package Database;

import java.sql.Connection;

public class AuthTableManager extends TableManager{
    public AuthTableManager(){
        super("users");
    }
    private String columns = "()";
    public boolean addCommand(Integer[] id){
        Connection connection = ServerConnection.getINSTANCE();
        try {
            connection.prepareStatement("INSERT INTO users ");
            return true;
        }
        catch (Exception e) {
            setLastError(e.getMessage());
            return false;
        }
    }
}
