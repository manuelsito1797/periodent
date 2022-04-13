package com.project.adapter.db;

import java.sql.Connection;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public interface IDBAdapter {
    Connection getConnection();
}
