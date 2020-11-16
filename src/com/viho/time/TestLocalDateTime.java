package com.viho.time;

import org.junit.Test;

import javax.annotation.Resource;
import java.time.*;

/**
 * author viho
 *
 * @create 2020-11-13下午 3:17
 */
public class TestLocalDateTime {
    /**
     * LocalDate LocalTime LocalDateTime
     */
    @Test
    public void test1(){
    //获取日期和时间
    LocalDateTime ldt = LocalDateTime.now();
    System.out.println("localDateTime = " + ldt);
    //设置指定日期
    LocalDateTime ldt2 = ldt.of(1997, 10, 10, 12, 30, 59);
    System.out.println(ldt2);
    //对对应位数进行加操作
    LocalDateTime localDateTime1 = ldt.plusYears(2);
    System.out.println(localDateTime1);
    //对对应位数进行减操作
    LocalDateTime ldt3 = ldt.minusMonths(3);
    System.out.println("localDateTime2 = " + ldt3);
    System.out.println("ldt.getYear() = " + ldt.getYear());
    System.out.println("ldt.getMonth() = " + ldt.getMonth());
    System.out.println("ldt.getDayOfMonth() = " + ldt.getDayOfMonth());
    System.out.println("ldt.getHour() = " + ldt.getHour());
    System.out.println("ldt.getMinute() = " + ldt.getMinute());
    System.out.println("ldt.getSecond() = " + ldt.getSecond());
}
//instant  时间戳（以unix元年1970年1月1日00:00:00 到现在的毫秒值）
    @Test
    public void test2(){
        Instant now = Instant.now();
        //默认获取UTC 时区 世界协调时间
        System.out.println("now = " + now);
        //设置时间偏移量 相差8小时
        OffsetDateTime odt = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println("odt = " + odt);
        //转为毫秒时间戳
        System.out.println("now.toEpochMilli() = " + now.toEpochMilli());
        //从1970.1.1 00:00:00加上1000秒
        Instant ins2 = Instant.ofEpochSecond(1000);
        System.out.println(ins2);
    }

    //Duration计算时间之间的间隔
    //Period  计算日期之间的间隔
    @Test
    public void test3(){
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();
        Duration between = Duration.between(ins1, ins2);
        //输出为1秒
        System.out.println("between = " + between.toMillis());
        System.out.println("---------------------------------");
        LocalTime now1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime now2 = LocalTime.now();
        System.out.println("Duration.between(now1,now2).toMillis() = " + Duration.between(now1, now2).toMillis());
        
    }
    @Test
    public void test4(){
        LocalDate ldt = LocalDate.now();
        LocalDate ldt2 = ldt.of( 12, 30, 59);
        Period bd = Period.between(ldt, ldt2);
        System.out.println("bd.getYears() = " + bd.getYears());
        System.out.println("bd.getMonths() = " + bd.getMonths());
        System.out.println("bd.getDays() = " + bd.getDays());
        /*System.out.println("bd.minusDays() = " + bd.minusDays());
        System.out.println("bd.minusYears() = " + bd.minusYears());*/
    }
}
