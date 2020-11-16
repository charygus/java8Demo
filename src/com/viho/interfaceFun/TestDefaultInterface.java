package com.viho.interfaceFun;

/**
 * author viho
 *接口中的默认方法
 *      若一个接口中定义一个默认方法 而另外一个父类或接口中又定义了一个同名的方法 时
 *          1.选择父类中的方法 如果一个父类提供了具体的实现 那么接口中具有相同名称和参数的默认方法会被忽略
 *          2.接口冲突。如果一个父类一个默认方法 而另一个接口也提供了一个具有相同名称和参数列表的方法
 *          那么必须覆盖该方法来解决冲突（对冲突方法 进行重写）
 * @create 2020-11-13下午 1:50
 */
public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());
        FunTest.show();
    }

}
