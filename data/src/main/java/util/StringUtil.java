package util;

/**
 * @author dhelarius 30/4/2022
 * periodent
 */
public class StringUtil {
    public static String addQuotes(String str) {
        return "\"" + str + "\"";
    }

    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
