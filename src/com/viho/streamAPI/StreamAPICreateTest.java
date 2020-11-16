package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * author viho
 *StreamAPI 对集合和数组中的数进行操作（在数据传输的过程中 可以进行相关的流水线式的操作）
 * 例如：在传输的过程中 对于数据进行筛选（数据源数据不会改变 但是数据谭数据的最终结果是经过过滤的）
 *  集合讲的是数据  流讲的是计算
 *注意：
 *  1.Stream自己不会存储元素
 *  2.Stream不会改变源对象 相反 他们会返回一个持有结果的新Stream
 *  3.Stream操作是延迟执行的 这以为这他们会等到需要结果的时候才执行
 *
 * Stream的操作三个步骤
 *  1.创建Stream（一个数据源(集合/数组) 获取一个流）
 *  2.中间操作（一个操作链 对数据源进行处理）
 *  3.终止操作（一个终止操作 执行中间操作链 并产生结果）
 *
 *
 *
 * @create 2020-11-09下午 4:41
 */

public class StreamAPICreateTest {
    //创建Steam
    @Test
    public void test1(){
        //通过collection系列提供的Stream()（串行流）或者parallelStream()（并行流）
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc", "dd");

        //4.创建无限流
        //迭代  集成function
        //从0开始进行x+2的操作 不停的迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        /*stream4.forEach(System.out::println);*/
        //4限定无限流的长度
        stream4.limit(10).forEach(System.out::println);
        //5.生成 (无限的制造对象)
        Stream.generate(() ->Math.random()).limit(10).forEach(System.out::println);
    }
}
