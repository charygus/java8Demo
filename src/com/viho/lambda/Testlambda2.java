package com.viho.lambda;

/**
 * author viho
 *
 * @create 2020-10-27下午 2:46
 */

import com.viho.entiy.Employee;
import org.junit.Test;
import sun.awt.SunHints;
import sun.rmi.runtime.Log;

import java.util.*;
import java.util.function.Consumer;

/**
 * lambda表达式的基础语法：
 *      在java8中引入一个新的操作符“->”该操作符为箭头操作符或lambda操作符
 *      箭头操作符将lambda表达式拆分成两部分：
 *          左侧：lambda表达式的参数列表
 *          右侧：lambda表达式中所需执行的功能 即 lambda体
 *
 *      语法格式1:无参数无返回值   ()-> System.out.println("hello");
 *      语法格式2:一个参数无返回值  (x)-> System.out.println(x);
 *                若只有一个参数参数的小括号可以省略不写
 *      语法格式3：有多个参数，并且lambda体中有多条语句有返回值
 *            Comparator<Integer> com = (x,y)->{
 *           System.out.println("函数式接口");
 *           return Integer.compare(x,y);
 *               };
 *      语法格式4：有多个参数 有返回值 lambda体只有一条语句 return和大括号都可以省略不写
 *          Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
 *      语法格式5：lambda表达式的参数列表可以不写参数类型（因为jvm编译器通过上下文推断出数据类型 这个过程称为类型推断） 但是要写参数类型 所有参数都得写
 *      左右遇一括号（左小 右大）省
 *      左侧推断类型省
 *
 *      二、lambda表达式需要函数式接口的支持
 *      函数式接口：接口中只有一个抽象方法的接口 称为函数式接口 可以使用@functionalInterface
 *          检查是否是函数式接口
 *
 */
public class Testlambda2 {

  @Test
    public void test1(){
      int num = 0;//jdk1.7以前得使用final修饰
      //局部内部类中应用同级别的变量得需要final修饰
      Runnable r =  new Runnable() {
          @Override
          public void run() {
              //不可以对num++
              System.out.println("hello world"+num);
          }
      };
      r.run();
      System.out.println("----------------------------------------------");
      Runnable r1 = ()-> System.out.println("hello lambda");
      r1.run();
  }
  @Test
  public void test2(){
      Consumer<String> con1 =(x)-> System.out.println(x);
      Consumer<String> con2 =x-> System.out.println(x);
      con1.accept("1111");
  }
  @Test
    public void test3(){
      Comparator<Integer> com = (x,y)->{
          System.out.println("函数式接口");
          return Integer.compare(x,y);
      };

  }
    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
    }

    //需求：对一个数进行运算
    @Test
    public void test5(){
        Integer result = operation(100, (x) -> x * x);
        System.out.println("result = " + result);
        Integer result2 = operation(25, x -> x + 10);
        System.out.println("result2 = " + result2);
    }
    public  Integer operation(Integer num,MyFunction mf){
      return mf.getValue(num);
    }

    /**
     * lambda练习
     */
    //定制排序Employee（先按年龄 年龄相同按姓名）
    @Test
    public void test6(){
        List<Employee> employeeList = Arrays.asList(
                new Employee("aaa",22,3000),
                new Employee("bbb",32,5000),
                new Employee("ccc",42,5000),
                new Employee("ddd",44,6000),
                new Employee("eee",46,7000)
        );
        //排序如果相等的 比较名字 如果不等 则比较年龄
        Collections.sort(employeeList,(e1,e2)->{
           if(e1.getAge()==e2.getAge()){
               return e1.getName().compareTo(e2.getName());
           }else{
               //加减号为倒叙
              return -Integer.compare(e1.getAge(),e2.getAge());
              // return Integer.compare(e1.getAge(),e2.getAge());
           }
        });
        employeeList.forEach(System.out::println);
    }

    //创建一个函数式接口实现小写转大写
    @Test
    public void getValue(){
    String text="\t\t\t  wowiw";
        String result = strHandler(text, (str) -> str.trim());
        String result2 = strHandler(text, (str) -> str.toUpperCase());
        System.out.println("result = " + result);
        System.out.println(result2);
    }
    public String strHandler(String str,GetValue gv){
        return gv.getValue(str);
    }

    //对于两个log处理
    public void logDouble(Long i,Long j,MathCount<Long,Long> mc){
        System.out.println(mc.count(i,j));
    }
    @Test
    public void test7(){
        logDouble(100L,200L,(x,y)->x+y);
        logDouble(100L,200L,(x,y)->x*y);
    }
}
