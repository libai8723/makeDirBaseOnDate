package io.github.libai8723;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * provide some commonly used method
 * 1. get the current date 's String
 */
public class Util {

    /**
     * get the current Date as yyyy_MM_dd HH_mm_ss format
     *
     * @return a string represent current time
     */
    public static String getCurDateStr(Date cur) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        return formatter.format(cur);
    }

    /**
     * remove all the invalid character for a dir name
     * \/:*?"<>|
     * @param str the input str
     * @return the str with all invalid string removed
     */
    public static String removeInvalidCharacter4DIRName(String str) {
        return str.replaceAll("[\\\\/:*?\"<>|]","");
    }

    /**
     * remove the leading spaces, ending spaces, convert multiple consecutive spaces into signle spaces
     * and remove the NewLine Characters based on the removeNewLine parameter
     * @param str the original string
     * @param removeNewLine whether remove new line character or not
     * @return a newly built string
     */
    public static String myTrim(final String str, boolean removeNewLine) {
        // 1st i want to replace new line character with space, and other white space character with space
        String newString;
        if(removeNewLine)
            newString = str.replaceAll("\\s"," ");
        else
            newString = str.replaceAll("[ \\t\\x0B\\f]"," ");
        // 2nd i want to replace consecutive space, make sure no consecutive space exist
        int strLength = newString.length();
        newString = newString.replaceAll(" {2}"," ");
        while(strLength > newString.length()){
            strLength = newString.length();
            newString = newString.replaceAll(" {2}"," ");
        }
        // 3rd then remove the leading and tailing space
        if(newString.length() > 0 && newString.charAt(0) == ' '){
            newString = newString.substring(1);
        }
        if(newString.endsWith(" ")){
            newString = newString.substring(0, newString.length()-1);
        }
        return newString;
    }

    /**
     * remove the leading spaces, ending spaces, convert multiple consecutive spaces into signle spaces
     * and remove the NewLine Characters
     * @param str the original string
     * @return a newly built string
     */
    public static String myTrim(final String str) {
        return myTrim(str, true);
    }
}
