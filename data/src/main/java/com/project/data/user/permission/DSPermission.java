package com.project.data.user.permission;

import com.project.adapter.db.DBFactory;
import com.project.adapter.db.IDBAdapter;
import com.project.domain.gateway.DsGateway;
import com.project.domain.user.model.permission.PermissionDsRequestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhelarius 17/4/2022
 * periodent
 */
public class DSPermission implements DsGateway<PermissionDsRequestModel> {

    private final IDBAdapter adapter;

    public DSPermission() {
        adapter = DBFactory.getAdapter();
    }

    @Override
    public void create(PermissionDsRequestModel requestModel) {

    }

    @Override
    public PermissionDsRequestModel read(int id) {
        return null;
    }

    @Override
    public List<PermissionDsRequestModel> readAll() {
        return null;
    }

    public List<PermissionDsRequestModel> readAllByUserId(int id) {
        List<PermissionDsRequestModel> permissions = new ArrayList<>();

        try {
            var sql = "SELECT p.f_id,p.f_descripcion,p.f_key,\n" +
                    "(SELECT (CASE WHEN pu.f_id_permiso = p.f_id THEN true ELSE false END)\n" +
                    "FROM t_permiso_usuario pu WHERE pu.f_id_usuario = 1 AND pu.f_id_permiso = p.f_id) AS asignado\n" +
                    "FROM t_permiso p LEFT JOIN t_permiso_usuario pu ON pu.f_id_permiso = p.f_id\n" +
                    "WHERE p.f_estado = true";
            var result = executeQuery(sql);

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
    public void update(PermissionDsRequestModel requestModel) {}

    @Override
    public void delete(int id) {}

    private PermissionDsRequestModel getPermissionFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String description = result.getString(2);
            String key = result.getString(3);
            boolean assigned = result.getBoolean(4);

            return new PermissionDsRequestModel(id, description, key, assigned);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
