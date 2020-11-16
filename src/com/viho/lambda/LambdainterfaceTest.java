package com.viho.lambda;

/**
 * author viho
 *java8四大内置函数接口
 * @create 2020-10-28上午 9:17
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * consumer<T>:消费型接口
 *  void accept(T t)
 *
 *  Supplier<T>供给型接口
 *  T get();
 *
 *  Funciton<T,R>函数型接口
 *   T为参数 R为返回值  R apply(T t);  t进行处理 r为返回值
 *
 *   Predicate<T>断言型接口 用于判断
 *      boolean test(T t)
 */
public class LambdainterfaceTest {
    //consumer<T>:消费型接口
    @Test
    public void test1() {
        happy(10000.00, m -> System.out.println(m));
    }

    public void happy(Double money, Consumer<Double> con) {
        con.accept(money);
    }

    //Supplier<T>供给型接口（返回一些对象）
    //产生指定个数整数放入集合
    @Test
    public void test2() {
    getNumList(4,()->(int)(Math.random()*100));
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        System.out.println(list.toString());
        return list;
    }
//Funciton<T,R>函数型接口
    //用于处理字符串  将长度大于3的字符串添加到数组中
    @Test
    public void  test3(){
        List<String> strList = Arrays.asList("aaa","bbbb","cc","ddddd","e");
        List<String> result = extract(strList, (s) -> s.length() > 3);
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
    public  String dealStr(String str1, Function<String,String> fun)
    {
        return fun.apply(str1);
    }
//断言型接口  Predicate<T>
    //讲满足条件的字符串添加到一个集合中
    public List<String> extract(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)){
                strList.add(s);
            }
        }
        return strList;
    }
}
