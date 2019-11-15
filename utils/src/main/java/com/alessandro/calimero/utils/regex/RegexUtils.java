package com.alessandro.calimero.utils.regex;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static Optional<String> getFirstMatch(String regex, String string){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.find() ? Optional.of(matcher.group()) : Optional.empty();
    }
}
