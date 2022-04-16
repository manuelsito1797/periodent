package com.project.data.user;

import com.project.adapter.db.DBFactory;
import com.project.adapter.db.IDBAdapter;
import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.UserDsRequestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class DSUser implements DsGateway<UserDsRequestModel> {

    private final IDBAdapter adapter;

    public DSUser() {
        adapter = DBFactory.getAdapter();
    }

    @Override
    public void create(UserDsRequestModel requestModel) {

    }

    @Override
    public UserDsRequestModel read(int id) {
        return null;
    }

    @Override
    public List<UserDsRequestModel> readAll() {
        List<UserDsRequestModel> users = new ArrayList<>();

        try {
            var sql = "select * from t_usuario";
            var result = executeQuery(sql);

            assert result != null;
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String lastname = result.getString(3);
                String dni = result.getString(4);
                String phone = result.getString(5);
                String email = result.getString(6);
                String username = result.getString(7);
                String password = result.getString(8);
                int createdBy = result.getInt(9);
                Timestamp creationDate = result.getTimestamp(10);
                boolean status = result.getBoolean(11);

                var current = new UserDsRequestModel(id, name, lastname, dni, phone, email,
                        username, password, createdBy, creationDate, status);

                users.add(current);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(UserDsRequestModel requestModel) {

    }

    @Override
    public void delete(int id) {

    }

    private ResultSet executeQuery(String sql) {
        var connection = adapter.getConnection();
        try {
            var statement = connection.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
