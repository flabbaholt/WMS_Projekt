package com.wms.util;

public class StringUtils {
   
        public static String toLowerCaseOrNA(String str) {
            if (str != null) {
                return str.toLowerCase();
            } else {
                return "N/A";
            }
        }
    
}
