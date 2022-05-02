package util;

import com.project.adapter.db.DBFactory;
import com.project.adapter.db.IDBAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class DBUtil {

    private final static IDBAdapter adapter = DBFactory.getAdapter();

    public static ResultSet executeQuery(String sql) {
        var connection = adapter.getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean execute(String sql) {
        var connection = adapter.getConnection();
        try {
            var statement = connection.prepareStatement(sql);
           return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
