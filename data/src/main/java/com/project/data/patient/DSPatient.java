package com.project.data.patient;

import com.project.domain.gateway.DsGateway;
import com.project.domain.patient.model.PatientDsRequestModel;
import util.DBUtil;
import util.StringUtil;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author dhelarius 25/6/2022
 * periodent
 */
public class DSPatient implements DsGateway<PatientDsRequestModel> {
    @Override
    public void create(PatientDsRequestModel requestModel) {
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var birthday = StringUtil.addQuotes(requestModel.getBirthday().toString());
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var address = StringUtil.addQuotes(requestModel.getAddress());
        var email = StringUtil.addQuotes(requestModel.getEmail());
        var createdBy = requestModel.getCreatedBy();

        var sql = "INSERT INTO t_paciente (f_nombre,f_apellido,f_fecha_nacimiento,f_cedula,f_telefono,f_direccion,f_email,f_creado_por) VALUES\n" +
                "("+ name +","+ lastname +","+ birthday +","+ dni +","+ phone +","+ address +","+ email +","+ createdBy +")";

        DBUtil.execute(sql);
    }

    @Override
    public PatientDsRequestModel read(int id) {
        var sql = "select * from t_paciente where f_id = " + id;
        return DBUtil.readRowFromQuery(sql, this::getPatientFromResult);
    }

    @Override
    public List<PatientDsRequestModel> readAll() {
        var sql = "select * from t_paciente";
        return DBUtil.readAllFromQuery(sql, this::getPatientFromResult);
    }

    @Override
    public void update(PatientDsRequestModel requestModel) {
        var name = StringUtil.addQuotes(requestModel.getName());
        var lastname = StringUtil.addQuotes(requestModel.getLastname());
        var birthday = StringUtil.addQuotes(requestModel.getBirthday().toString());
        var dni = StringUtil.addQuotes(requestModel.getDni());
        var phone = StringUtil.addQuotes(requestModel.getPhone());
        var address = StringUtil.addQuotes(requestModel.getAddress());
        var email = StringUtil.addQuotes(requestModel.getEmail());

        var sql = "UPDATE t_paciente SET f_nombre = "+ name +", f_apellido = "+ lastname +", f_fecha_nacimiento = "+ birthday +", f_cedula = "+ dni +", \n" +
                "f_telefono = "+ phone +", f_direccion = "+ address +", f_email = "+ email +" WHERE f_id =" + requestModel.getId();

        DBUtil.execute(sql);
    }

    @Override
    public void delete(int id) {
        var sql = "UPDATE t_paciente SET f_activo = false WHERE f_id =" + id;
        DBUtil.execute(sql);
    }

    @Override
    public PatientDsRequestModel readLast() {
       var sql = "SELECT * FROM t_paciente WHERE f_id = (SELECT MAX(f_id) FROM t_paciente)";
       return DBUtil.readRowFromQuery(sql, this::getPatientFromResult);
    }

    private PatientDsRequestModel getPatientFromResult(ResultSet result) {
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

            return new PatientDsRequestModel(id, name, lastname, birthday, dni, phone, address, email, createdBy, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
