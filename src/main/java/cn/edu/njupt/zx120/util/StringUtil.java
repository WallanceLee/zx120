package cn.edu.njupt.zx120.util;

import java.util.regex.Pattern;

/**
 * Created by wallance on 2/23/17.
 */
public class StringUtil {

    private static final Pattern numericPattern = Pattern.compile("[0-9]]*");

    public static boolean isNumeric(final String str) { return numericPattern.matcher(str).matches(); }

    public static boolean isNullOrEmpty(String str) { return str == null || str.trim().length() == 0; }

}
