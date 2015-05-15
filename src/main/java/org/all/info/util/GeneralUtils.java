package org.all.info.util;

public class GeneralUtils {

    public static int convertLongToInt(Object longValue){
        return Integer.valueOf(new Long((Long)longValue).intValue());
    }
}
