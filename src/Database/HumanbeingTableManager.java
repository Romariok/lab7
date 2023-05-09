package Database;

import Data.HumanBeing;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HumanbeingTableManager extends TableManager {
    private final String columns = "name, x, y, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, carCool";

    public HumanbeingTableManager() {
        super("humanbeing");
    }

    public boolean updateCommand(HumanBeing hb, long id,String user) {
        try {
            PreparedStatement preparedStatement = ServerConnection.getINSTANCE().prepareStatement("UPDATE humanbeing SET (" + columns + ") (?,?,?,?,?,?,?,?,?,?) WHERE id=? and user = ?");
            prepare(preparedStatement, hb);
            preparedStatement.setLong(11, id);
            preparedStatement.setString(12,user);
            int res = preparedStatement.executeUpdate();
            if (res != 0) {
                return true;
            }
            throw new Exception("No such element with such id: " + id);
        } catch (Exception e) {
            setLastE(e.getMessage());
            return false;
        }
    }

    private void prepare(PreparedStatement preparedStatement, HumanBeing hb) throws SQLException {
        preparedStatement.setString(1, hb.getName());
        preparedStatement.setInt(2, hb.getCoordinates().getX());
        preparedStatement.setLong(3, hb.getCoordinates().getY());
        preparedStatement.setBoolean(4, hb.isRealHero());
        preparedStatement.setBoolean(5, hb.getHasToothpick());
        preparedStatement.setLong(6, hb.getImpactSpeed());
        preparedStatement.setString(7, hb.getSoundtrackName());
        preparedStatement.setString(8, hb.getWeaponType().toString());
        preparedStatement.setString(9, hb.getMood().toString());
        preparedStatement.setBoolean(10, hb.getCar().getCool());
    }

    public boolean deleteCommand(Long id, String mode, String user) {
        try {
            PreparedStatement preparedStatement = ServerConnection.getINSTANCE().prepareStatement("DELETE FROM humanbeing WHERE id" +mode+"? and user = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2,user);
            int res = preparedStatement.executeUpdate();
            if (res != 0) {
                return true;
            }
            throw new SQLException("No such element for removing");
        } catch (SQLException e) {
            setLastE(e.getMessage());
            return false;
        }

    }

    public boolean insertCommand(HumanBeing hb) {
        try {
            PreparedStatement preparedStatement = ServerConnection.getINSTANCE().prepareStatement("INSERT INTO humanbeing (" + columns + ",login) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            prepare(preparedStatement, hb);
            preparedStatement.setString(11, hb.getLogin());
            int res = preparedStatement.executeUpdate();
            if(res!=0){
                return true;
            }
            throw new SQLException("Something went wrong, cannot add this element (may be element already exists)");
        }
        catch (SQLException e) {
            setLastE(e.getMessage());
            return false;
        }
    }
}
