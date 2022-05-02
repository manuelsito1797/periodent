package com.project.app.util;

/**
 * @author dhelarius 28/4/2022
 * periodent
 */
public class CheckUtil {

    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
