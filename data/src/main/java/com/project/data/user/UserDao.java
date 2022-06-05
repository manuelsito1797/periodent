package com.project.data.user;

import com.project.domain.user.model.CommonUser;
import util.DBUtil;

/**
 * @author dhelarius 30/5/2022
 * periodent
 */
public class UserDao {

    public void applyUserPermissions(CommonUser user) {
        var sql = "";
        var permissions = user.getPermissions();
        for(var permission : permissions) {
            if(permission.isAssigned()) {
                sql = "REPLACE INTO t_permiso_usuario (f_id_usuario, f_id_permiso)\n" +
                        "VALUES ("+ user.getId() +", "+ permission.getId() +")";
            } else {
                sql = "DELETE FROM t_permiso_usuario WHERE f_id_usuario = "+
                        user.getId() +" AND f_id_permiso =" + permission.getId();
            }
            DBUtil.execute(sql);
        }
    }
}
