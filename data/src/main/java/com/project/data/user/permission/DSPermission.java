package com.project.data.user.permission;

import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.permission.PermissionDsRequestModel;
import util.DBUtil;
import util.StringUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * @author dhelarius 27/4/2022
 * periodent
 */
public class DSPermission implements DsGateway<PermissionDsRequestModel> {
    @Override
    public void create(PermissionDsRequestModel requestModel) {
        var description = StringUtil.addQuotes(requestModel.getDescription());
        var key = StringUtil.addQuotes(requestModel.getKey());

        var sql = "INSERT INTO t_permiso (f_descripcion, f_key) " +
                "VALUES (" + description + ", " + key +")";
        DBUtil.execute(sql);
    }

    @Override
    public PermissionDsRequestModel read(int id) {
        try {
            var sql = "SELECT * FROM t_permiso WHERE f_id = " + id;
            var result = DBUtil.executeQuery(sql);

            assert result != null;
            if(!result.next()) return null;

            return getPermissionFromResult(result);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PermissionDsRequestModel> readAll() {
        List<PermissionDsRequestModel> permissions = new ArrayList<>();

        try {
            var sql = "SELECT * FROM t_permiso WHERE f_estado = true";
            var result = DBUtil.executeQuery(sql);

            assert result != null;
            while (result.next()) {
                var current = getPermissionFromResult(result);
                permissions.add(current);
            }
            return permissions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(PermissionDsRequestModel requestModel) {
        var id = requestModel.getId();
        var description = StringUtil.addQuotes(requestModel.getDescription());
        var key = StringUtil.addQuotes(requestModel.getKey());

        var sql = "UPDATE t_permiso SET f_descripcion = " + description + ", " +
                "f_key = " + key + " WHERE f_id = " + id;

        DBUtil.execute(sql);
    }

    @Override
    public void delete(int id) {
        var sql = "UPDATE t_permiso SET f_estado = false WHERE f_id = " + id;
        DBUtil.execute(sql);
    }

    @Override
    public PermissionDsRequestModel readLast() {
        return null;
    }

    private PermissionDsRequestModel getPermissionFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String description = result.getString(2);
            String key = result.getString(3);
            boolean active = result.getBoolean(4);

            return new PermissionDsRequestModel(id, description, key, active);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
