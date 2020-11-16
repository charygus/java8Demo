package com.viho.method;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/**
 * author viho
 * Java8中方法引用以及构造器引用
 * @create 2020-11-09下午 2:23
 * 1.方法引用：lambda体中的内用有方法已经实现了 我们可以使用 方法引用
 *          可以理解方法引用是lambda 表达式的另一种表现形式
 * 三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 *  使用注意：
 *      1.lambda体中调用方法的参数列表与返回值类型 要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *      2.若lambda参数列表中的第一参数是实例方法的调用者 第二个参数是实例方法的参数时 则可以使用类名::实例方法
 *
 *2.构造器引用
 *  格式：
 *      类名::new
 *  需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 */
public class TestMethodRef {
    //对象 实例方法
    @Test
    public void  test1(){
        PrintStream ps = System.out;
        Consumer<String> con = (x)-> ps.println(x);
        //需要
        PrintStream ps1 = System.out;
        //方法引用的形式（前提：实现的接口抽象方法参数和返回值类型与实现方法的参数和返回值类型 保持一致）
        Consumer<String> con1 =ps1::println;
        Consumer<String> con2 = System.out::println;
        con2.accept("111111");
    }
    //对象 实例方法
    @Test
    public void test2(){
        Employee employee = new Employee("aaa",23,2000.00);
        Supplier<String> sup = ()->employee.getName();
        Supplier<Integer> sup2 = employee::getAge;
        String s = sup.get();
        Integer s1 = sup2.get();
        System.out.println(s1);
        System.out.println(s);
    }
    //类 静态方法
    @Test
    public void test3(){
        //比较两个int值大小
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        //compare方法返回-1则x<y 返回1则x>y x=y则返回0
        Comparator<Integer> com2 = Integer::compare;
        int compare = com2.compare(3, 2);
        System.out.println("compare = " + compare);
    }

    //类::实例方法名
    //比较两个字符串是否一样
    @Test
    public void test4(){
        //两个参数 第一个参数是方法的调用者 第二个参数是方法的参数时 就可以使用类名::实例方法
        BiPredicate<String,String> bi1 = (x,y)->x.equals(y);
        BiPredicate<String,String> bi2 = String::equals;
        boolean equals = bi2.test("1","3");
        System.out.println("equals = " + equals);
    }
    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> sup = ()->new Employee();
        //构造器引用方式   无参构造器
        Supplier<Employee> sup1 = Employee::new;
        Employee employee = sup1.get();
        System.out.println("employee = " + employee);
        //传进去name返回一个emp对象
        Function<String,Employee> fun1 = (x)->new Employee(x);
        Function<String,Employee> fun2 =Employee::new;
        Employee emp = fun2.apply("sam");
        System.out.println("emp = " + emp);

        BiFunction<String,Integer,Employee> bi = Employee::new;
        Employee emp2 = bi.apply("sam", 17);
        System.out.println("emp2 = " + emp2);
    }
    //数组引用(数组集合 也是对象 可以用array::new 引用)
    @Test
    public void test6(){
        Function<Integer,String[]> fun1 = (x)->new  String[x];
        Function<Integer,String[]> fun2 = String[]::new;
        String[] strArr1 = fun1.apply(10);
        String[] strArr2 = fun2.apply(10);
        System.out.println(strArr2.length);
    }
}
