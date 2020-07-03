package com.oneday.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Abhilash on 29/03/2018.
 */

public class ValidationRegex {

    public static boolean isRegexValid(final String value, final String regex) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
