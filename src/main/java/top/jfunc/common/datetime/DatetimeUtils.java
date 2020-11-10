package top.jfunc.common.datetime;


import top.jfunc.common.utils.StrUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        if (StrUtil.isNotEmpty(format)) {
            return new SimpleDateFormat(format).format(date);
        } else {
            return new SimpleDateFormat(SDF_DATETIME).format(date);
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
            if (StrUtil.isNotEmpty(pattern)) {
                return new SimpleDateFormat(pattern).parse(dateStr);
            } else {
                return new SimpleDateFormat(SDF_DATETIME).parse(dateStr);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    ///////////////////////////////////////////以下实现参考JFinal/////////////////////////////////////////////
    /**
     * 缓存线程安全的 DateTimeFormatter
     */
    private static final Map<String, DateTimeFormatter> FORMATERS = new HashMap<>();

    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        DateTimeFormatter ret = FORMATERS.get(pattern);
        if (ret == null) {
            synchronized (FORMATERS) {
                ret = DateTimeFormatter.ofPattern(pattern);
                FORMATERS.put(pattern, ret);
            }
        }
        return ret;
    }

    /**
     * 按指定 pattern 将当前时间转换成 String
     * 例如：now("yyyy-MM-dd HH:mm:ss")
     */
    public static String now(String pattern) {
        return LocalDateTime.now().format(getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 LocalDateTime 转换成 String
     * 例如：format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss")
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 LocalDate 转换成 String
     */
    public static String format(LocalDate localDate, String pattern) {
        return localDate.format(getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 LocalTime 转换成 String
     */
    public static String format(LocalTime localTime, String pattern) {
        return localTime.format(getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 String 转换成 LocalDateTime
     */
    public static LocalDateTime parseLocalDateTime(String localDateTimeString, String pattern) {
        return LocalDateTime.parse(localDateTimeString, getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 String 转换成 LocalDate
     */
    public static LocalDate parseLocalDate(String localDateString, String pattern) {
        return LocalDate.parse(localDateString, getDateTimeFormatter(pattern));
    }

    /**
     * 按指定 pattern 将 String 转换成 LocalTime
     */
    public static LocalTime parseLocalTime(String localTimeString, String pattern) {
        return LocalTime.parse(localTimeString, getDateTimeFormatter(pattern));
    }

    /**
     * java.util.Date --> java.time.LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        // java.sql.Date 不支持 toInstant()，需要先转换成 java.util.Date
        if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * java.util.Date --> java.time.LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        // java.sql.Date 不支持 toInstant()，需要先转换成 java.util.Date
        if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * java.util.Date --> java.time.LocalTime
     */
    public static LocalTime toLocalTime(Date date) {
        // java.sql.Date 不支持 toInstant()，需要先转换成 java.util.Date
        if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * java.time.LocalDateTime --> java.util.Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalDate --> java.util.Date
     */
    public static Date toDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalTime --> java.util.Date
     */
    public static Date toDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * java.time.LocalTime --> java.util.Date
     */
    public static Date toDate(LocalDate localDate, LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
}
