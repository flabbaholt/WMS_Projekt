package com.wms.util;

public class StringUtils {
   
        // converts a string to lower case or returns "N/A" if the string is null
        public static String toLowerCaseOrNA(String str) {
            if (str != null) {
                return str.toLowerCase();
            } else {
                return "N/A";
            }
        }
    
}
