package Database;

import java.sql.*;

public class TableManager {

    private final String table;

    public TableManager(String table) {
        this.table = table;
    }

    private Exception lastE;

    // Method to execute an update query
    public boolean updateCommand(String columns, String args, String condition) {
        String sql = new StringBuilder().append("UPDATE ").append(table).append(" SET ").append(columns).append(" = ? ").append(condition).toString();
        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, args);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            lastE = e;
            System.out.println(sql + args);
            return false;
        }
        return true;
    }

    // Method to execute a select query
    public String selectCommand(String columns) throws Exception{
        StringBuilder result = new StringBuilder();
        String sql = new StringBuilder().append("SELECT ").append(columns).append(" FROM ").append(table).toString();
        Connection connection = ServerConnection.getINSTANCE();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String columnValue;
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                int columnType = resultSet.getMetaData().getColumnType(i);
                if (columnType == Types.TIMESTAMP) {
                    Timestamp time = resultSet.getTimestamp(i);
                    columnValue = time.toString();
                } else {
                    if (columnType == Types.BIGINT || columnType == Types.INTEGER) {
                        columnValue = String.valueOf(resultSet.getLong(i));
                    } else if (columnType == Types.NUMERIC) {
                        columnValue = String.valueOf(resultSet.getFloat(i));
                    } else if (columnType == Types.BOOLEAN) {
                        columnValue = String.valueOf(resultSet.getBoolean(i));
                    } else {
                        columnValue = resultSet.getString(i);
                    }
                }
                result.append(columnValue);
                if (i != resultSet.getMetaData().getColumnCount()) {
                    result.append("\n");
                }
            }
            result.append("\n\n");
        }
        return result.toString();
    }

    // Method to execute an insert query
    public boolean insertCommand(String columns, String values) {
        String sql = new StringBuilder().append("INSERT INTO ").append(table).append(" ").append(columns).append(" VALUES ").append(values).toString();
        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            lastE = e;
            System.out.println(sql + values);
            return false;
        }
        return true;
    }

    // Method to execute a delete query
    public boolean deleteCommand(String condition) {
        String sql = new StringBuilder().append("DELETE FROM ").append(table).append(" ").append(condition).toString();
        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            lastE = e;
            return false;
        }
        return true;
    }

    public static String buildColumns(String columns) {
        StringBuilder b = new StringBuilder();
        b.append("(");
        for (String t : columns.split(" ")
        ) {
            b.append(" ").append(t);
        }
        b.append(")");
        return b.toString();
    }

    public String getLastE(){
        return lastE.getMessage();
    }
}