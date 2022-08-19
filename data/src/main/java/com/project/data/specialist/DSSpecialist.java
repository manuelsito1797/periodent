package com.project.data.specialist;

import com.project.domain.gateway.DsGateway;
import com.project.domain.specialist.model.specialist.SpecialistDsRequestModel;
import util.DBUtil;
import util.StringUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author dhelarius 11/7/2022
 * periodent
 */
public class DSSpecialist implements DsGateway<SpecialistDsRequestModel> {
    @Override
    public void create(SpecialistDsRequestModel requestModel) {
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var birthday = requestModel.getBirthday().toString();
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var address = StringUtil.addQuotes(requestModel.getAddress());
        var email = StringUtil.addQuotes(requestModel.getEmail());
        var createdBy = requestModel.getCreatedBy();

        var sql = "INSERT INTO t_especialista (f_nombre,f_apellido,f_fecha_nacimiento,f_cedula,f_telefono,f_direccion,f_email,f_creado_por)\n" +
                "VALUES ("+ name +","+ lastname +","+ birthday +","+ dni +","+ phone +","+ address +","+ email +","+ createdBy +")";

        DBUtil.execute(sql);
    }

    @Override
    public SpecialistDsRequestModel read(int id) {
        var sql = "select * from t_especialista where f_id = " + id;
        return DBUtil.readRowFromQuery(sql, this::getSpecialistFromResult);
    }

    @Override
    public List<SpecialistDsRequestModel> readAll() {
        var sql = "select * from t_paciente";
        return DBUtil.readAllFromQuery(sql, this::getSpecialistFromResult);
    }

    @Override
    public void update(SpecialistDsRequestModel requestModel) {
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var birthday = StringUtil.addQuotes(requestModel.getBirthday().toString());
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var address = StringUtil.addQuotes(requestModel.getAddress());
        var email = StringUtil.addQuotes(requestModel.getEmail());

        var sql = "UPDATE t_especialista SET f_nombre = "+ name +", f_apellido = "+ lastname +", f_fecha_nacimiento = "+ birthday +", f_cedula = "+ dni +", \n" +
                "f_telefono = "+ phone +", f_direccion = "+ address +", f_email = "+ email +" WHERE f_id =" + requestModel.getId();

        DBUtil.execute(sql);
    }

    @Override
    public void delete(int id) {
        var sql = "UPDATE t_especialista SET f_activo = false WHERE f_id =" + id;
        DBUtil.execute(sql);
    }

    @Override
    public SpecialistDsRequestModel readLast() {
        var sql = "SELECT * FROM t_especialista WHERE f_id = (SELECT MAX(f_id) FROM t_especialista)";
        return DBUtil.readRowFromQuery(sql, this::getSpecialistFromResult);
    }

    private SpecialistDsRequestModel getSpecialistFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String name = result.getString(2);
            String lastname = result.getString(3);
            Date birthday = result.getDate(4);
            String dni = result.getString(5);
            String phone = result.getString(6);
            String address = result.getString(7);
            String email = result.getString(8);
            int createdBy = result.getInt(9);
            boolean status = result.getBoolean(11);

            return new SpecialistDsRequestModel(id, name, lastname, birthday, dni, phone,
                    address, email, createdBy, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
