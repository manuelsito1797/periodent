package com.project.adapter.db.postgresql;

import com.project.adapter.db.IDBAdapter;
import com.project.adapter.util.PropertyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;

/**
 * @author djimenez on 29/3/2022
 * periodental
 */
public class PostgreSQLAdapter implements IDBAdapter {

    static {
        try {
            new org.postgresql.Driver();
        } catch(Exception e) {}
    }

    @Override
    public Connection getConnection() {
        try {
            String connectionString = getConnectionString();

            Properties p = PropertyUtil.load(PostgreSQLAdapter.class);
            assert p != null;
            String user = p.getProperty(PropertyUtil.USER);
            String password = p.getProperty(PropertyUtil.PASSWORD);

            Connection connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Connection class => " + connection.getClass().getCanonicalName());
            return connection;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getConnectionString(){
        return Objects.requireNonNull(PropertyUtil.load(PostgreSQLAdapter.class)).getProperty(PropertyUtil.CONNECTION);
    }
}
