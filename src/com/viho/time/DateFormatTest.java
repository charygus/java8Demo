package com.viho.time;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * author viho
 * DataFormatter  格式化时间/日期
 * @create 2020-11-16上午 9:00
 */
public class DateFormatTest {

    @Test
    public void test1(){
        //DateTimeFormatter中有对应所需的格式
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println("strDate = " + strDate);
        //转为自定义格式
        System.out.println("--------------------------------------------------------------");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = ldt.format(dtf2);
        System.out.println("strDate2 = " + strDate2);
        //时间字符串转为时间格式
        LocalDateTime ldt2 = ldt.parse(strDate2,dtf2);
        System.out.println("ldt2 = " + ldt2);
    }

    //带时区的时间日期API ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test2(){
        //获取所有时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
        //获取指定时区的时间
        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("America/Bahia"));
        System.out.println("ldt1 = " + ldt1);
        //这种方式为带时区的时间
        LocalDateTime ldt2 = LocalDateTime.now();
        //为时间设置时区 显示当前时间和指定时区时间之间的时差 2020-11-16T09:33:28.507-03:00[America/Bahia]
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("America/Bahia"));
        System.out.println("zdt = " + zdt);
    }
}
