package org.beyondpn.hui.crawler.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by jianhua on 2016/10/9.
 */
public class ConvertUtil {

    private static final String STRIP_CHARS = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static int toInt(String str){
        return Integer.parseInt(strip(str));
    }

    public static float toFloat(String str){
        return Float.parseFloat(strip(str));
    }

    public static String strip(String str){
        str = StringUtils.strip(str);
        return StringUtils.strip(str,STRIP_CHARS);
    }

    public static Date parseDate(String str){
        return Date.from(parseLocalDate(str).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate parseLocalDate(String str){
        return LocalDate.parse(str,FORMATTER);
    }
}
