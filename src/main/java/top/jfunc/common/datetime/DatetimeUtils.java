package top.jfunc.common.datetime;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具
 * @author 熊诗言
 */
public class DatetimeUtils {
    public static final String SDF_DATETIME       = "yyyy-MM-dd HH:mm:ss";
    public static final String SDF_DATETIME_SHORT = "yyyyMMddHHmmss";
    public static final String SDF_DATETIME_MS    = "yyyyMMddHHmmssSSS";
    public static final String SDF_DATE           = "yyyy-MM-dd";

    /**
     * 字符串转日期
     * @param dateStr 日期字符串
     * @return 日期 yyyy-MM-dd HH:mm:ss
     */
    public static Date toDate(String dateStr) {
        return toDate(dateStr, null);
    }

    /**
     * 日期转字符串
     * @param date 日期
     * @return 字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String toStr(Date date) {
        return toStr(date, SDF_DATETIME);
    }

    /**
     * 日期转字符串
     * @param date 日期
     * @param format 格式化字符串
     * @return 字符串
     */
    public static String toStr(Date date, String format) {
        SimpleDateFormat sdf = null;
        if (null != format && !"".equals(format)) {
            sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            sdf = new SimpleDateFormat(SDF_DATETIME);
            return sdf.format(date);
        }
    }

    /**
     * 字符串转日期
     * @param dateStr 日期字符串
     * @param pattern 格式化字符串
     * @return 日期
     */
    public static Date toDate(String dateStr, String pattern) {
        try {
            if (null != pattern && !"".equals(pattern)) {
                return new SimpleDateFormat(pattern).parse(dateStr);
            } else {
                return new SimpleDateFormat(SDF_DATETIME).parse(dateStr);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
