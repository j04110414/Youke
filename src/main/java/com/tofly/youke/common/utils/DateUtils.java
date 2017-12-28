package com.tofly.youke.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String DATE_PATTERN = "yyyy-MM-dd";
    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final static String[] PARSE_DATE_PATTERN = new String[]{
            "yyyy",
            "yyyyMM", "yyyy-MM", "yyyy/MM",
            "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
            "yyyyMMddHH", "yyyy-MM-ddHH", "yyyy/MM/ddHH",
            "yyyyMMddHHmm", "yyyy-MM-ddHHmm", "yyyy/MM/ddHHmm",
            "yyyyMMddHHmmss", "yyyy-MM-ddHHmmss", "yyyy/MM/ddHHmmss",
            "yyyyMMddHHmmssS", "yyyy-MM-ddHHmmssS", "yyyy/MM/ddHHmmssS",
            "yyyyMMddHHmmssSS", "yyyy-MM-ddHHmmssSS", "yyyy/MM/ddHHmmssSS",
            "yyyyMMddHHmmssSSS", "yyyy-MM-ddHHmmssSSS", "yyyy/MM/ddHHmmssSSS",
            "yyyyMMddHHmmssSSSS", "yyyy-MM-ddHHmmssSSSS", "yyyy/MM/ddHHmmssSSSS",
            "yyyyMMdd HH", "yyyy-MM-dd HH", "yyyy/MM/dd HH",
            "yyyyMMdd HH:mm", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm",
            "yyyyMMdd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss",
            "yyyyMMdd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss", "yyyy/MM/dd'T'HH:mm:ss",
            "yyyyMMdd HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss.S", "yyyy/MM/dd HH:mm:ss.S",
            "yyyyMMdd'T'HH:mm:ss.S", "yyyy-MM-dd'T'HH:mm:ss.S", "yyyy/MM/dd'T'HH:mm:ss.S",
            "yyyyMMdd HH:mm:ss.SS", "yyyy-MM-dd HH:mm:ss.SS", "yyyy/MM/dd HH:mm:ss.SS",
            "yyyyMMdd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy/MM/dd HH:mm:ss.SSS",
            "yyyyMMdd'T'HH:mm:ss.SSS", "yyyy-MM-dd'T'HH:mm:ss.SSS", "yyyy/MM/dd'T'HH:mm:ss.SSS",
            "yyyyMMdd HH:mm:ss.SSSS", "yyyy-MM-dd HH:mm:ss.SSSS", "yyyy/MM/dd HH:mm:ss.SSSS",
            "yyyy年",
            "yyyy年MM月",
            "yyyy年MM月dd日",
            "yyyy年MM月dd日HH", "yyyy年MM月dd日 HH", "yyyy年MM月dd日 HH",
            "yyyy年MM月dd日HH:mm", "yyyy年MM月dd日 HH:mm", "yyyy年MM月dd日 HH:mm",
            "yyyy年MM月dd日HH:mm:ss", "yyyy年MM月dd日 HH:mm:ss", "yyyy年MM月dd日 HH:mm:ss",
            "yyyy年MM月dd日HH:mm:ss.S", "yyyy年MM月dd日 HH:mm:ss.S", "yyyy年MM月dd日 HH:mm:ss.S",
            "yyyy年MM月dd日HH:mm:ss.SS", "yyyy年MM月dd日 HH:mm:ss.SS", "yyyy年MM月dd日 HH:mm:ss.SS",
            "yyyy年MM月dd日HH:mm:ss.SSS", "yyyy年MM月dd日 HH:mm:ss.SSS", "yyyy年MM月dd日 HH:mm:ss.SSS",
            "yyyy年MM月dd日HH:mm:ss.SSSS", "yyyy年MM月dd日 HH:mm:ss.SSSS", "yyyy年MM月dd日 HH:mm:ss.SSSS"};

    /**
     * getDateTimeNow 根据传入的格式产生当前日期
     *
     * @return
     */
    public static String getDateTimeNow() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * getDateTimeNow 根据传入的格式产生当前日期
     *
     * @param format
     * @return
     */
    public static String getDateTimeNow(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * getDateTimeAny:(根据传入的Date转换成固定格式日期时间格式). <br/>
     *
     * @param date
     * @return
     */
    public static String getDateTimeAny(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 根据传入的格式、字符串产生当前日期
     *
     * @param format
     * @return
     * @throws ParseException
     */
    public static String getDateTimeAny(String dateString, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateString = sdf.format(parseDate(dateString));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateString;
    }


    /**
     * 根据传入的日期字符串产生时间值
     * dateString
     *
     * @return long
     * @throws ParseException
     */
    public static long getDateTimeLong(String dateString) {
        long time = -1L;
        try {
            Date date = parseDate(dateString);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 根据传入的日期字符串产生当前日期对象
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateTimeObject(String dateString) {
        Date res = null;
        try {
            res = parseDate(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * getMaxDateOrMinDate:(获取某日期的最大时间或者最小时间). <br/>
     *
     * @param date
     * @param type
     * @return
     * @author tom
     */
    public static Date getMinMaxDate(String date, String type) throws ParseException {
        date = getDateTimeAny(date, "yyyy-MM-dd");
        if ("min".equals(type)) {
            date += " 00:00:00";
        }
        if ("max".equals(type)) {
            date += " 23:59:59";
        }
        Date dateResult = null;
        try {
            dateResult = parseDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateResult;
    }

    /**
     * getWeekOfDate:(获取星期几). <br/>
     *
     * @return String @return
     * @author salman
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 比较两个日期之间的大小
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        return result >= 0;
    }

    /**
     * compareDate:(比较字符串类型的数据的大小). <br/>
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     * @author tom
     */
    public static boolean compareDate(String d1, String d2) {
        boolean bool = false;
        try {
            Date date1 = parseDate(d1);
            Date date2 = parseDate(d2);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(date1);
            c2.setTime(date2);
            int result = c1.compareTo(c2);
            if (result >= 0) {
                bool = true;
            }
        } catch (ParseException e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * timeBetween:(返回两个日期的对比结果:N秒前，N分钟前，N小时前，N天前等等). <br/>
     *
     * @return String@return
     * @author salman
     */
    public static Map<String, String> timeBetween(String smallDate, String bigDate) {
        Map<String, String> compareResult = new HashMap<String, String>(16);
        String textTime = "";

        try {
            long oldTime = getDateTimeObject(smallDate).getTime();
            long nowTime = getDateTimeObject(bigDate).getTime();

            long second = (nowTime - oldTime) / 1000L;
            second = second <= 0 ? 1 : second;
            long minute = (nowTime - oldTime) / (1000L * 60);
            minute = second > 59 && minute == 0 ? 1 : minute;
            long hour = (nowTime - oldTime) / (1000L * 60 * 60);
            hour = minute > 59 && hour == 0 ? 1 : hour;
            long day = (nowTime - oldTime) / (1000L * 60 * 60 * 24);
            day = hour > 23 && day == 0 ? 1 : day;
            long month = (nowTime - oldTime) / (1000L * 60 * 60 * 24 * 30);
            month = day > 30 && month == 0 ? 1 : month;
            long year = (nowTime - oldTime) / (1000L * 60 * 60 * 24 * 365);
            year = month > 12 && year == 0 ? 1 : year;

            compareResult.put("second", second + "");
            compareResult.put("minute", minute + "");
            compareResult.put("hour", hour + "");
            compareResult.put("day", day + "");
            compareResult.put("month", month + "");
            compareResult.put("year", year + "");

            if (year > 0) {
                textTime = year + "年前";
            } else if (month > 0) {
                textTime = month + "月前";
            } else if (day > 0) {
                textTime = day + "天前";
            } else if (hour > 0) {
                textTime = hour + "小时前";
            } else if (minute > 0) {
                textTime = minute + "分钟前";
            } else {
                textTime = second + "秒前";
            }

        } catch (Exception e) {
            textTime = "不久前";
        }

        compareResult.put("txtTime", textTime);
        return compareResult;
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(String dateStr) {
        Calendar cal = Calendar.getInstance();
        Date birthday = null;
        int age = 0;
        try {
            birthday = getDateTimeObject(dateStr);
            if (cal.before(birthday)) {
                throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(birthday);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

    /**
     * getConstellation:(计算星座). <br/>
     *
     * @param d
     * @return String
     * @author Issac
     */
    public static String getConstellation(Date d) {
        int month = d.getMonth() + 1;
        int day = d.getDate();
        String constellation = "";
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {
            constellation = "水瓶座";
        }
        if (month == 1 && day > 31) {
            constellation = "Huh?";
        }
        if (month == 2 && day >= 19 || month == 3 && day <= 20) {
            constellation = "双鱼座";
        }
        if (month == 2 && day > 29) {
            constellation = "Say what?";
        }
        if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            constellation = "白羊座";
        }
        if (month == 3 && day > 31) {
            constellation = "OK.  Whatever.";
        }
        if (month == 4 && day >= 20 || month == 5 && day <= 20) {
            constellation = "金牛座";
        }
        if (month == 4 && day > 30) {
            constellation = "I'm soooo sorry!";
        }
        if (month == 5 && day >= 21 || month == 6 && day <= 21) {
            constellation = "双子座";
        }
        if (month == 5 && day > 31) {
            constellation = "Umm ... no.";
        }
        if (month == 6 && day >= 22 || month == 7 && day <= 22) {
            constellation = "巨蟹座";
        }
        if (month == 6 && day > 30) {
            constellation = "Sorry.";
        }
        if (month == 7 && day >= 23 || month == 8 && day <= 22) {
            constellation = "狮子座";
        }
        if (month == 7 && day > 31) {
            constellation = "Excuse me?";
        }
        if (month == 8 && day >= 23 || month == 9 && day <= 22) {
            constellation = "处女座";
        }
        if (month == 8 && day > 31) {
            constellation = "Yeah. Right.";
        }
        if (month == 9 && day >= 23 || month == 10 && day <= 22) {
            constellation = "天秤座";
        }
        if (month == 9 && day > 30) {
            constellation = "Try Again.";
        }
        if (month == 10 && day >= 23 || month == 11 && day <= 21) {
            constellation = "天蝎座";
        }
        if (month == 10 && day > 31) {
            constellation = "Forget it!";
        }
        if (month == 11 && day >= 22 || month == 12 && day <= 21) {
            constellation = "射手座";
        }
        if (month == 11 && day > 30) {
            constellation = "Invalid Date";
        }
        if (month == 12 && day >= 22 || month == 1 && day <= 19) {
            constellation = "摩羯座";
        }
        if (month == 12 && day > 31) {
            constellation = "No way!";
        }
        return constellation;
    }

    /**
     * 获取Long时间集合，值为每天00:00:00
     *
     * @return
     */
    public static List<Long> getDaysLong(String startTime, String endTime) {
        List<Long> days = new ArrayList<Long>();
        try {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(parseDate(getDateTimeAny(startTime, "yyyy-MM-dd")));

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(parseDate(getDateTimeAny(endTime, "yyyy-MM-dd")));
            if (endCalendar.getTimeInMillis() < startCalendar.getTimeInMillis()) {
                startCalendar.setTime(parseDate(getDateTimeAny(endTime, "yyyy-MM-dd")));
                endCalendar.setTime(parseDate(getDateTimeAny(startTime, "yyyy-MM-dd")));
            }
            endCalendar.add(Calendar.DAY_OF_MONTH, 1);

            while (true) {
                days.add(new Long(startCalendar.getTimeInMillis()));

                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
                if (startCalendar.getTimeInMillis() >= endCalendar.getTimeInMillis()) {
                    break;
                }
            }
        } catch (ParseException e) {
            logger.error("获取时间段内日期数失败", e);
        }
        return days;
    }

    /**
     * 获取指定月起至时间, 精确到秒
     * 例如：2016-09-01 00:00:00 ~ 2016-09-30 23:59:59
     * @param year
     * @param month
     * @return
     */
    public static List<Long> getFirstAndEndOfMonth(int year, int month) {
        List<Long> days = new ArrayList<Long>();
        try {
            Date date = parseDate(getDateTimeAny(year+"-"+month, "yyyy-MM"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            // 获取前月的第一天
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            days.add(calendar.getTimeInMillis());

            // 获取前月的最后一天
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            days.add(calendar.getTimeInMillis());
        } catch (Exception e) {
            logger.error("获取指定月起至时间失败", e);
        }
        return days;
    }

    /**
     * 将字符串格式化为日期
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        Date date;
        try {
            date = org.apache.commons.lang3.time.DateUtils.parseDateStrictly(dateStr, PARSE_DATE_PATTERN);
        } catch (Exception ex) {
            logger.error("严格模式日期转换错误，将采用非严格模式转换，请核实转换数据");
            date = org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, PARSE_DATE_PATTERN);
        }
        return date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();

        return Integer.parseInt(String.valueOf((time2 - time1) / (1000 * 3600 * 24)));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
