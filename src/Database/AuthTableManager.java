package Database;

import java.sql.*;

public class AuthTableManager extends TableManager {
    public AuthTableManager() {
        super("users");
    }

    private final String columns = "(login, pass, salt)";

    public boolean insertCommand(String[] user) {
        Connection connection = ServerConnection.getINSTANCE();
        try {
            PreparedStatement stat = connection.prepareStatement("INSERT INTO users " + columns + " VALUES (?,?,?)");
            stat.setString(1, user[0]);
            stat.setString(2, user[1]);
            stat.setString(3, user[2]);
            int res = stat.executeUpdate();
            if (res == 0) {
                throw new SQLException("Something wrong: such user already exist");
            }
            return true;
        } catch (Exception e) {
            setLastE(e.getMessage());
            return false;
        }
    }

    public boolean selectCommand(String login, String pass) {
        ResultSet resultSet;
        try {
            PreparedStatement stat = ServerConnection.getINSTANCE().prepareStatement("SELECT " + columns + " FROM " + " users " + " WHERE login = ? AND pass = ?");
            stat.setString(1, login);
            stat.setString(2, pass);
            resultSet = stat.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            throw new SQLException("Incorrect login or password");
        } catch (Exception e) {
            setLastE(e.getMessage());
            return false;
        }
    }

    public String getSalt(String login) throws Exception {
        PreparedStatement stat = ServerConnection.getINSTANCE().prepareStatement("SELECT (salt) FROM users WHERE login = ?");
        stat.setString(1, login);
        ResultSet resultSet = stat.executeQuery();
        if (!resultSet.next()) {
            throw new SQLException("No such user found");
        }
        return resultSet.getString(0);
    }

    public String getPepper() throws SQLException{
        return ServerConnection.getINSTANCE().prepareStatement("SELECT (login) FROM users WHERE id=0").executeQuery().getString(0);
    }
}

