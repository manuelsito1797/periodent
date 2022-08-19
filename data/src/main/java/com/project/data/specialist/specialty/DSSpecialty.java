package com.project.data.specialist.specialty;

import com.project.domain.gateway.DsGateway;
import com.project.domain.specialist.model.specialty.SpecialtyDsRequestModel;
import util.DBUtil;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author dhelarius 9/7/2022
 * periodent
 */
public class DSSpecialty implements DsGateway<SpecialtyDsRequestModel> {
    @Override
    public void create(SpecialtyDsRequestModel requestModel) {
        var description = StringUtil.addQuotes(requestModel.getDescription());
        var sql = "INSERT INTO t_especialidad (f_descripcion) VALUES ("+ description +")";
        DBUtil.execute(sql);
    }

    @Override
    public SpecialtyDsRequestModel read(int id) {
        var sql = "select * from t_especialidad where f_id = " + id;
        return DBUtil.readRowFromQuery(sql, this::getSpecialtyFromResult);
    }

    @Override
    public List<SpecialtyDsRequestModel> readAll() {
        var sql = "select * from t_especialidad";
        return DBUtil.readAllFromQuery(sql, this::getSpecialtyFromResult);
    }

    @Override
    public void update(SpecialtyDsRequestModel requestModel) {
        var description = StringUtil.addQuotes(requestModel.getDescription());
        var sql = "UPDATE SET t_especialidad f_descripcion = " + description + "  WHERE f_id = " + requestModel.getId();
        DBUtil.execute(sql);
    }

    @Override
    public void delete(int id) {
        var sql = "UPDATE t_especialidad SET f_activo = false WHERE f_id =" + id;
        DBUtil.execute(sql);
    }

    @Override
    public SpecialtyDsRequestModel readLast() {
        var sql = "SELECT * FROM t_especialidad WHERE f_id = (SELECT MAX(f_id) FROM t_especialidad)";
        return DBUtil.readRowFromQuery(sql, this::getSpecialtyFromResult);
    }

    private SpecialtyDsRequestModel getSpecialtyFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String description = result.getString(2);
            boolean status = result.getBoolean(3);

            return new SpecialtyDsRequestModel(id, description, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
