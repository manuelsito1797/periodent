package com.project.adapter.db;

import com.project.adapter.db.mysql.MySQLAdapter;
import com.project.adapter.db.postgresql.PostgreSQLAdapter;
import com.project.adapter.util.PropertyUtil;

import java.util.Properties;

/**
 * @author djimenez on 29/3/2022
 * periodental
 */
public class DBFactory {

    private static final String DB_TYPE = "dbadaptertype";

    public static IDBAdapter getAdapter(DBType type) {
        switch(type) {
            case POSTGRESQL:
                return new PostgreSQLAdapter();
            case MYSQL:
                return new MySQLAdapter();
            default:
                return null;
        }
    }

    public static IDBAdapter getAdapter(){
        try {
            Properties p = PropertyUtil.load(DBFactory.class);

            assert p != null;
            String dbtype = p.getProperty(DB_TYPE);

            return (IDBAdapter)Class.forName(dbtype).getDeclaredConstructor().newInstance();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
