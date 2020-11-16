package com.viho.streamAPI;

import com.viho.entiy.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * author viho
 *StreamAPI中间操作
 * @create 2020-11-10上午 9:44
 *多个中间操作可以连接起来形成一个流水线 除非流水线上触发终止操作 否则中间操作不会执行任何处理
 * 而在终止操作是一次性全部处理 称为 惰性求值
 */
public class StreamAPIApplicationTest {
    List<Employee> employeeList = Arrays.asList(
            new Employee("aaa",22,3000),
            new Employee("bbb",32,5000),
            new Employee("ccc",42,5000),
            new Employee("ddd",44,6000),
            new Employee("eee",46,7000),
            new Employee("eee",46,7000),
            new Employee("eee",46,7000)
    );
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

    @Test
    public void test1(){
        //参数为断言型lambda   中间操作不会有任何结果的 不执行任何操作
        //内部迭代（迭代操作由StreamAPI执行）
        Stream<Employee> stream = employeeList.stream()
                .filter((e)->
                        {
                            //每一个值都执行输出语句 当满足终止条件时 执行终止操作
                            System.out.println("StreamAPI中间操作");
                            return e.getAge()>40;
                        }
                        );
        //终止操作 一次性执行全部的内容  即为惰性求值
        stream.forEach(System.out::println);
    }
    @Test
    public void test2(){
        //外部迭代  Iterator使用迭代器迭代
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.hasNext());
        }
    }

    /**
     * limit
     */
    @Test
    public void test3() {
    employeeList.stream().
            //中间操作
            filter((x)->{
                System.out.println("中间操作执行");
               return x.getAge() > 40;
            }).
            //当获取满足条件的条数时 就不在执行 则称为短路
            limit(2).
            //终止操作
            forEach(System.out::println);
    }

    /**
     * 跳过前若干个符合条件的值 从第n+1个开始取
     */
    @Test
    public void test4() {
    employeeList.stream().filter((e)->e.getSalay()>=5000)
            .skip(2).forEach(System.out::println);
    }
/**
 * 去重
 * 利用流生成的hashcode和equals进行筛选 所以得对实体的equals和hashcode进行重写
 */
@Test
    public void test5(){
    employeeList.stream().filter(
            (e)->e.getSalay()>5000
    ).distinct().forEach(System.out::println);
}

    /*
    映射
    map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    */
@Test
    public void test6(){
    List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    list.stream()
            .map((str)->str.toUpperCase())
            .forEach(System.out::println);
    System.out.println("_______________________");
    employeeList.stream().map(Employee::getName)
            .forEach(System.out::println);
    System.out.println("_______________________");
    Stream<Stream<Character>> stream2 = list.stream().map(StreamAPIApplicationTest::filterCharacter);
    stream2.forEach(
            (sm)->{
                //取出为一个个字符
               sm.forEach(System.out::println);
            }

    );
    System.out.println("_______________________");
    //对于在map中取出元素在存入得转流两次 所以可以使用flatmap
    //直接得到的是字符流
    Stream<Character> characterStream = list.stream()
            .flatMap(StreamAPIApplicationTest::filterCharacter);
    characterStream.forEach(System.out::println);
}


@Test
public void test7(){
    List<String> list1 = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    ArrayList<Object> list2 = new ArrayList<>();
    list2.add(list1);
    list2.add("111");
    ArrayList<Object> list3 = new ArrayList<>();
    System.out.println(list2);
    list3.addAll(list1);
    System.out.println(list3);
}
public static Stream<Character> filterCharacter(String str){
    ArrayList<Character> list = new ArrayList<>();
    //字符串转字符数组 再将字符提取出来
    for (Character character : str.toCharArray()){
        list.add(character);
    }
    return  list.stream();
}
    }

