package com.project.data.specialist;

import com.project.domain.specialist.model.specialist.CommonSpecialist;
import com.project.domain.specialist.model.specialist.Specialty;
import util.DBUtil;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author dhelarius 10/7/2022
 * periodent
 */
public class SpecialistDao {

    public void applySpecialties(CommonSpecialist specialist) {

        // Eliminar Especialidades no asignadas
        deleteUnassignedSpecialties(specialist);

        // Asignar especialidades
        var sql = "";
        var specialties = specialist.getSpecialties();

        for(var specialty : specialties) {
            sql = "INSERT IGNORE INTO t_especialidad_especialista (f_especialista,f_especialidad,f_asignado_por) " +
                    "VALUES ("+ specialist.getId() +","+ specialty.getId() +","+ specialty.getAssignedBy() +")";

            DBUtil.execute(sql);
        }
    }

    public List<Specialty> readAllSpacialtiesBySpecialistId(int id) {
        var sql = "SELECT te.f_id,te.f_descripcion,tee.f_asignado_por\n" +
                "FROM t_especialidad_especialista tee INNER JOIN t_especialidad te ON tee.f_especialidad = te.f_id\n" +
                "WHERE tee.f_especialista = " + id;
        return DBUtil.readAllFromQuery(sql, this::getSpecialtyFromResult);
    }

    private void deleteUnassignedSpecialties(CommonSpecialist specialist) {
        String sql;
        // Obtener id de especialidades
        var ids = getIdsFromSpecialties(specialist.getSpecialties());

        sql = "DELETE FROM t_especialidad_especialista WHERE f_especialista = "+ specialist.getId() +
                " AND f_especialidad NOT IN("+ ids +")";

        DBUtil.execute(sql);
    }

    private String getIdsFromSpecialties(List<Specialty> specialties) {
        // Construir cadena con los ids de las especialidades separados por coma
        var builder = new StringBuilder();

        for(var specialty : specialties) {
            builder.append(specialty.getId()).append(",");
        }

        return StringUtil.removeLastChar(builder.toString());
    }

    private Specialty getSpecialtyFromResult(ResultSet result) {
        try {
            int id = result.getInt(1);
            String description = result.getString(2);
            int assignedBy = result.getInt(3);

            return new Specialty(id, description, assignedBy);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
