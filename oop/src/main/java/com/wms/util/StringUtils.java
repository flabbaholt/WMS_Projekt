package com.wms.util;

public class StringUtils {
        // Helper Class for String Operations
        // converts a string to lower case or returns "N/A" if the string is null
        public static String toLowerCaseOrNA(String str) {
            if (str != null) {
                return str.toLowerCase();
            } else {
                return "N/A";
            }
        }
    
}
