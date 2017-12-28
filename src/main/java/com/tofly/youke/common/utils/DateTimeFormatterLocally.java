package com.tofly.youke.common.utils;

import java.time.format.DateTimeFormatter;

/**
 * Desc: 本地常用的时间格式<br/>
 *
 * @author: Summer
 * @date: 2017/10/24 10:18
 */
public class DateTimeFormatterLocally {

    public static final DateTimeFormatter LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   /*
    public static void main(String[] args) {
        // eg:
        LocalDateTime eg1 = LocalDateTime.parse("2017-10-24 10:18:25", LOCAL_DATE_TIME);
        LocalDate eg2 = LocalDate.parse("2017-10-24", LOCAL_DATE);
    }*/
}
