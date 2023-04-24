package Database;

import java.sql.*;
import java.time.ZonedDateTime;

public class TableManager {

    private final String table;

    public TableManager(String table) {
        this.table = table;
    }

    // Method to execute an update query
    public boolean updateCommand(String columns, String args) {
        String sql = new StringBuilder().append("UPDATE ").append(table).append(" SET ").append(columns).append(" = ?").toString();

        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, args);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Method to execute a select query
    public String selectAllCommand() {
        StringBuilder result = new StringBuilder();
        String sql = new StringBuilder().append("SELECT*FROM ").append(table).toString();
        try {
            Connection connection = ServerConnection.getINSTANCE();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String columnValue;
                    int columnType = resultSet.getMetaData().getColumnType(i);
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    if (columnType == Types.TIMESTAMP) {
                        Timestamp time = resultSet.getTimestamp(i);
                        columnValue = time.toString();
                    } else {
                        if (columnType == Types.BIGINT || columnType == Types.INTEGER) {
                            columnValue = String.valueOf(resultSet.getLong(i));
                        } else if (columnType == Types.NUMERIC) {
                            columnValue = String.valueOf(resultSet.getFloat(i));
                        } else if (columnType == java.sql.Types.BOOLEAN) {
                            columnValue = String.valueOf(resultSet.getBoolean(i));
                        } else {
                            columnValue = resultSet.getString(i);
                        }
                    }
                    result.append(columnValue).append(" ");
                }
                if (resultSet.next()) {
                    result.append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result.toString();
    }

    // Method to execute an insert query
    public boolean insertCommand(String columns, String values) {
        String sql = new StringBuilder().append("INSERT INTO ").append(table).append(" (").append(columns).append(") VALUES (").append(values).append(")").toString();
        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Method to execute a delete query
    public boolean deleteCommand(String condition) {
        String sql = new StringBuilder().append("DELETE FROM ").append(table).append(" WHERE ").append(condition).toString();
        try (Connection connection = ServerConnection.getINSTANCE();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
}