package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * author viho
 * 终止操作
 *StreamAPI查找和匹配
 * @create 2020-11-11下午 1:43
 */
public class StreamAPISelectTest {
    List<Employee> employeeList = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    /*查找与匹配
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test1(){
        //allMatch——检查是否匹配所有元素匹配所有元素是否满足条件 返回Boolean值
        boolean match1 = employeeList.stream()
                .allMatch((emp) -> emp.getStatus().equals(Employee.Status.BUSY));
        System.out.println("match1 = " + match1);

        //anyMatch——检查是否至少匹配一个元素
        boolean match2 = employeeList.stream()
                .anyMatch((emp) -> emp.getStatus().equals(Employee.Status.BUSY));
        System.out.println("match2 = " + match2);

        //noneMatch——检查是否没有匹配的元素
        boolean match3 = employeeList.stream()
                .noneMatch((emp) -> emp.getStatus().equals(Employee.Status.BUSY));
        System.out.println("match3 = " + match3);

        //findFirst——返回第一个元素  按工资排序 查询工资最高的人
        //查询的对象存放到Optional容器中
        Optional<Employee> first = employeeList.stream()
                .sorted((e1,e2)->-Double.compare(e1.getSalay(), e2.getSalay()))
                .findFirst();
        //当Optional中值为空 则有一个替代的对象 避免空指针异常
        first.orElse(new Employee());
        System.out.println(first.get());

        //findAny——返回当前流中的任意元素 一线程 找到则取出
        Optional<Employee> any1 = employeeList.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println("any1.get() = " + any1.get());
        //使用并行流  多个线程同时找
        Optional<Employee> any2 = employeeList.parallelStream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println("any2.get() = " + any2.get());
    }

    /**
     * 查询流中元素个数和最大/最小值
     */
    @Test
    public void test2(){
        long count = employeeList.stream().count();
        System.out.println("count = " + count);
        //返回对象
        Optional<Employee> maxage = employeeList.stream().max((e1,e2)->Double.compare(e1.getAge(),e2.getAge()));
        Optional<Employee> minage = employeeList.stream().min((e1,e2)->Double.compare(e1.getAge(),e2.getAge()));
        System.out.println("maxage.get() = " + maxage.get());
        System.out.println("minage.get() = " + minage.get());
        //获取工资最少的工资是多少
        Optional<Double> minSalary = employeeList.stream().map(Employee::getSalay).min((x, y) -> Double.compare(x, y));
        System.out.println("minSalary = " + minSalary);
    }
}
