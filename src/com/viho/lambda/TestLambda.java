package com.viho.lambda;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * author viho
 *
 * @create 2020-10-26下午 10:16
 */
public class TestLambda {
    /**
     * 匿名内部类
     */
    @Test
    public  void  test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //整段代码就这句有用
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> tree = new TreeSet<>(com);
    }
    //lambda表达式实现
    @Test
    public  void  test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> tree = new TreeSet<>(com);
    }


    //數組轉list
    List<Employee> employeeList = Arrays.asList(
            new Employee("aaa",22,3000),
            new Employee("bbb",32,4000),
            new Employee("ccc",42,5000),
            new Employee("ddd",44,6000),
            new Employee("eee",46,7000)
    );
    //查询年龄大于30的员工
        public List<Employee> findEmployeeByAge(List<Employee> list){
            ArrayList<Employee> emps = new ArrayList<>();
            for (Employee e : list) {
                if (e.getAge()>30) {
                    emps.add(e);
                }
            }
            System.out.println(emps.size());
            return emps;
        }

    //查询年龄大于30且工资大于4000的员工
    public List<Employee> findEmployeeByAgeAndSalay(List<Employee> list){
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee e : list) {
            if (e.getAge()>30&&e.getSalay()>4000) {
                emps.add(e);
            }
        }
        System.out.println(emps.size());
        return emps;
    }
    @Test
    public  void test3(){
        List<Employee> enployeeByAge = findEmployeeByAge(employeeList);
        //System.out.println(enployeeByAge.toString());
        List<Employee> employeeByAgeAndSalay = findEmployeeByAgeAndSalay(employeeList);
        System.out.println(employeeByAgeAndSalay.toString());
    }
    //优化方式:策略设计模式
    public List<Employee> findEmployee(List<Employee> list,Mypredicate<Employee> filter){
        ArrayList<Employee> results = new ArrayList<>();
        for (Employee employee : list) {
            if (filter.test(employee)){
                results.add(employee);
            }
        }
        return results;
    }
    @Test
    public  void  test4(){
        List<Employee> findEmployee = findEmployee(employeeList, new FilterEmployee());
        System.out.println(findEmployee);
    }

    //优化方法：匿名内部类
    @Test
    public  void  test5(){
        List<Employee> findEmployee = findEmployee(employeeList, new Mypredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalay()>5000&&employee.getAge()>35;
            }
        });
        System.out.println(findEmployee);
    }
    //优化三：lambda表达式
    //test类中不能有返回值也不能有参数
    //还是使用匿名内部类的方式省去创建方法的过程
    @Test
    public void findEmployee(){
        List<Employee> findResult1 = findEmployee(employeeList, e -> e.getSalay() <= 5000);
        List<Employee> findResult2 = findEmployee(employeeList, employee -> employee.getAge() > 40 && employee.getSalay() > 5000);
        System.out.println(findResult1);
        System.out.println(findResult2);
    }
    //优化方式四：Stream API
    @Test
    public void test6(){
            //取list中满足条件的 且只取两个
        Stream<Employee> employeeStream = employeeList.stream().filter((e) -> e.getAge() > 40 && e.getSalay() > 3000).limit(2);
        employeeStream.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");
        //获取list中所有人的name
        Stream<String> nameStream = employeeList.stream().map(Employee::getName);
        nameStream.forEach(System.out::println);
    }
}
