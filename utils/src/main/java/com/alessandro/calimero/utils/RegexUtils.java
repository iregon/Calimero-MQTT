package com.alessandro.calimero.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static String match(String regex, String string){
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(string);
        return matcher.group(0);
    }
}
