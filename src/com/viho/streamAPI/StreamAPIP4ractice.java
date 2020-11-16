package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * author viho
 *StreamAPI练习
 * @create 2020-11-12上午 9:01
 */
public class StreamAPIP4ractice {
    List<Employee> employeeList = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    /*
    1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
    ，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
 */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().map((x)->x*x).forEach(System.out::println);
    }
    /*
     2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
     */
    @Test
    public void test2(){
        Optional<Integer> total = employeeList.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println("total = " + total);

        Long collect = employeeList.stream().collect(Collectors.counting());
        System.out.println("collect = " + collect);
    }



}
