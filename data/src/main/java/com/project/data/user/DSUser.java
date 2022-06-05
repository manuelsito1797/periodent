package com.project.data.user;

import com.project.adapter.db.DBFactory;
import com.project.adapter.db.IDBAdapter;
import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.UserDsRequestModel;
import util.DBUtil;
import util.StringUtil;

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
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var email = StringUtil.addQuotes(requestModel.getEmail());
        var username = StringUtil.addQuotes(requestModel.getUsername());
        var password = StringUtil.addQuotes(requestModel.getPassword());
        var createdBy = requestModel.getCreatedBy();

        var sql = "INSERT INTO t_usuario (f_nombre,f_apellido,f_cedula,f_telefono,f_email,f_usuario,f_password,f_creado_por) VALUES\n" +
                "("+ name +","+ lastname +","+ dni +","+ phone +","+ email +","+ username +","+ password +","+ createdBy +")";

        DBUtil.execute(sql);
    }

    @Override
    public UserDsRequestModel read(int id) {
        var sql = "select * from t_usuario where f_id = " + id;
        var result = DBUtil.executeQuery(sql);

        try {
            assert result != null;
            if(!result.next()) return null;

            return getUserFromResult(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserDsRequestModel> readAll() {
        List<UserDsRequestModel> users = new ArrayList<>();

        try {
            var sql = "select * from t_usuario";
            var result = DBUtil.executeQuery(sql);

            assert result != null;
            while (result.next()) {
                var current = getUserFromResult(result);
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
        var id = requestModel.getId();
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var email = StringUtil.addQuotes(requestModel.getEmail());
        var username = StringUtil.addQuotes(requestModel.getUsername());
        var password = requestModel.getPassword();

        var sql = "UPDATE t_usuario SET f_nombre="+ name +", f_apellido="+ lastname +", f_cedula="+ dni +" , " +
                "f_telefono="+ phone +", f_email="+ email +", f_usuario="+ username;

        if(!password.isEmpty()) {
            password = StringUtil.addQuotes(password);
            sql += ", f_password=" + password;
        }

        sql += " WHERE f_id=" + id;

        DBUtil.execute(sql);
    }

    @Override
    public void delete(int id) {

    }

    private UserDsRequestModel getUserFromResult(ResultSet result) {
        try {
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

            return new UserDsRequestModel(id, name, lastname, dni, phone, email,
                    username, password, createdBy, creationDate, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
