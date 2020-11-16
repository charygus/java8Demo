package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * author viho
 *StreamAPI排序
 * 	sorted()——自然排序（按照string写好的规则排序）
 * 	sorted(Comparator com)——定制排序
 * @create 2020-11-10下午 10:11
 */
public class StreamAPISortTest {

    List<Employee> employeeList = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    //自然排序
    @Test
    public void test1(){
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list1.stream()
                .sorted()
        .forEach(System.out::println);
        System.out.println("--------------------------------------");
        employeeList.stream().sorted(
                (e1,e2)->{
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return -String.valueOf(e1.getAge()).compareTo(String.valueOf(e2.getAge()));
                    }

                }).forEach(System.out::println);
    }
}
