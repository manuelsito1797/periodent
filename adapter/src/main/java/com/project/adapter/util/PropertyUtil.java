package com.project.adapter.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author dhelarius 10/4/2022
 * periodent
 */
public class PropertyUtil {

    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String CONNECTION = "connection";

    public static Properties load(Class<?> clazz) {
        try {
            Properties p = new Properties();
            InputStream stream =
                    clazz.getClassLoader().getResourceAsStream("META-INF/adapter.properties");
            p.load(stream);
            return p;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
