/**
 * @author dhelarius 10/4/2022
 * periodent
 */module adapter {

     requires domain;

     requires java.sql;
     requires mysql.connector.java;
     requires org.postgresql.jdbc;
     requires modelmapper;

     exports com.project.adapter.db;
     exports com.project.adapter.security.cipher;
     exports com.project.adapter.converter.modelmapper;
}