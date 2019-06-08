package xyz.sheba.commit_template.utils;

public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static String truncate(String s, int n) {
        return s.substring(0, Math.min(s.length(), n));
    }
}
