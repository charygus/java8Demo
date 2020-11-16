package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author viho
 *  归约与收集
 *
 *  归约（给定一个起始值 然后对需要处理的值给出对应的处理逻辑 如果有起始值 则返回的是对应数据类型 如果没有起始值 则返回optional 因为防止空指针）
 *  reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
 *
 * @create 2020-11-11下午 3:07
 */

public class StreamAPIReduceTest {
    List<Employee> employeeList = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     * ——可以将流中元素反复结合起来，得到一个值。
     */

    @Test
    public void test1() {
        //1返回的是对应数据类型 2返回的是optional 因为1不可能为空 有起始值 2可能为空
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //reduce两个参数 起始值（类似于sum 每次x与y的和 存入identity 然后再进行累加）
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("-----------------------");

        //统计工资的总和
        //使用map和reduce的连接通常称为map-reduce模式
        Optional<Double> reduce = employeeList.stream().map(Employee::getSalay).reduce(Double::sum);
        System.out.println(reduce.get());

    }
    /*收集  collect——将流转换为其他形式。
     接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     将所有人姓名放入一个集合中
     */
    @Test
    public void test2(){
        List<String> nameList = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
       nameList.forEach(System.out::println);
        System.out.println("-----------------------");
        //对于流中的数据去重
        Set<String> collect = employeeList.stream()
                .map((e) -> e.getName())
                .collect(Collectors.toSet());
    collect.forEach(System.out::println);
        System.out.println("-----------------------");
        //放入hashset中  放入linkedhashset
        HashSet<String> collect1 = employeeList.stream()
                .map((x) -> x.getName())
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void test5(){
        //总数
        Long counting = employeeList.stream()
                .collect(Collectors.counting());
        //平均值
        Double avg = employeeList.stream()
                .collect(Collectors.averagingDouble(
                        Employee::getSalay)
                );
        //总和
        Double collect = employeeList.stream()
                .collect(Collectors.summingDouble(Employee::getSalay));
        //最大值
        Optional<Employee> maxSalary = employeeList.stream()
                .collect(Collectors
                        .maxBy(
                                (e1, e2) -> Double.compare(
                                        e1.getSalay(), e2.getSalay()
                                )
                        )
                );
        System.out.println("maxSalary = " + maxSalary);
        //最小值
        employeeList.stream()
                .map((e)->e.getSalay())
                .collect(Collectors.minBy((e1,e2)->Double.compare(e1,e2)));
    }
    //对集合进行分组
    @Test
    public void test(){
        Map<Employee.Status, List<Employee>> statusGroup = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getStatus
                ));
        System.out.println("statusGroup = " + statusGroup);
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = employeeList.stream()
                .collect(
                        Collectors.groupingBy(Employee::getStatus,
                                Collectors.groupingBy(
                                        (e) -> {
                                            if (e.getAge() <= 35) {
                                                return "青年";
                                            } else if (e.getAge() <= 50) {
                                                return "中年";
                                            } else {
                                                return "老年";
                                            }
                                        }
                                )
                        )
                );
        System.out.println("collect = " + collect);
    }
    //对集合中的数据进行分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = employeeList.stream()
                .collect(Collectors
                        .partitioningBy((e) -> e.getSalay() > 8000));
        System.out.println(collect);
        //直接分为false和true两个区
    }

    /**
     * summarizingDouble
     */
    @Test
    public void test9(){
    DoubleSummaryStatistics dss = employeeList.stream()
            .collect(Collectors
                    .summarizingDouble(Employee::getSalay));
    System.out.println("dss.getAverage() = " + dss.getAverage());
    System.out.println("dss.getCount() = " + dss.getCount());
    System.out.println("dss.getMax() = " + dss.getMax());
    System.out.println("dss.getMin() = " + dss.getMin());
}

    /**
     * joining  连接成字符串(可以添加分隔符)
     */
    @Test
    public void tset11(){
    String collect = employeeList.stream()
            .map(Employee::getName)
            .collect(Collectors.joining(","));
    System.out.println("collect = " + collect);
}
}
