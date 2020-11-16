package com.viho.interfaceFun;

/**
 * author viho
 *接口中默认方法和静态方法
 * @create 2020-11-13下午 1:43
 */
public interface FunTest {
//default修饰符修饰接口中默认方法
    default String getName(){
        System.out.println("哈哈哈");
        return "哈哈哈";
    }
    public static void  show(){
        System.out.println("接口中的静态方法");
    }
}
