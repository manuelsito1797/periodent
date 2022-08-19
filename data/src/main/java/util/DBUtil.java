package util;

import com.project.adapter.db.DBFactory;
import com.project.adapter.db.IDBAdapter;
import com.project.domain.patient.model.PatientDsRequestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class DBUtil {

    @FunctionalInterface
    public interface Result<R> {
        R onResult(ResultSet resultSet);
    }

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

    public static <R> R readRowFromQuery(String sql, Result<R> result) {
        var resultSet = DBUtil.executeQuery(sql);

        try {
            assert resultSet != null;
            if(!resultSet.next()) return null;

            return result.onResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <R> List<R> readAllFromQuery(String sql, Result<R> result) {
        List<R> rows = new ArrayList<>();
        try {
            var resultSet = DBUtil.executeQuery(sql);

            assert resultSet != null;
            while (resultSet.next()) {
                var current = result.onResult(resultSet);
                rows.add(current);
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
