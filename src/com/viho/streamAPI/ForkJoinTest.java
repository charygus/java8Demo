package com.viho.streamAPI;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * author viho
 *
 * @create 2020-11-12下午 1:49
 */
public class ForkJoinTest {

    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinCalculate task = new ForkJoinCalculate(0, 10000000000l);
        Long sum = forkJoinPool.invoke(task);
        System.out.println("sum = " + sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
    }

    /**
     * 普通for循环
     */
    @Test
    public void test2() {
        Instant start = Instant.now();
    long sum = 0l;
        for (long i = 0; i <= 10000000000l; i++) {
            sum+=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

    }

    /**
     * java8并行流
     */
    @Test
    public void test3(){
        //顺序流
        LongStream.rangeClosed(0,100000000000l)
                .sequential()
                .reduce(0,Long::sum);
        //串行流  底层实现的原理为fork-join
        LongStream.rangeClosed(0,100000000000l)
                .parallel()
                .reduce(0,Long::sum);
    }
    }

