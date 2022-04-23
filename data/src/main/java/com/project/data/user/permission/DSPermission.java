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
            var sql = "SELECT p.f_id,p.f_descripcion,\n" +
                    "(SELECT (CASE WHEN pu.f_id_permiso = p.f_id THEN true ELSE false END)\n" +
                    "FROM t_permiso_usuario pu WHERE pu.f_id_usuario = " + id + ") AS asignado\n" +
                    "FROM t_permiso p LEFT JOIN t_permiso_usuario pu ON pu.f_id_permiso = p.f_id " +
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
    public void update(PermissionDsRequestModel requestModel) {

    }

    @Override
    public void delete(int id) {

    }

    private PermissionDsRequestModel getPermissionFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String description = result.getString(2);
            boolean assigned = result.getBoolean(3);

            return new PermissionDsRequestModel(id, description, assigned);
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
