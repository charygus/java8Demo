package com.viho.optional;

import com.viho.entiy.Employee;
import com.viho.entiy.Godness;
import com.viho.entiy.Man;
import com.viho.entiy.NewMan;
import jdk.nashorn.internal.ir.ReturnNode;
import org.junit.Test;

import java.util.Optional;

/**Optional容器类
 * author viho
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 * @create 2020-11-12下午 2:38
 */
public class TestOptional {
    @Test
    public void test1(){
        //如果optional容器为null 则直接在optional里显示空指针
        //of不可以构建空的
        Optional<Employee> op1 = Optional.of(null);
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println("employee.get() = " + employee.get());
    }
    @Test
    public void test2(){
        //构建一个空的optional
        Optional<Object> op = Optional.empty();
        System.out.println("op.get() = " + op.get());
    }

    @Test
    public void test3(){
        //构建对应obj的容器（可有值可为空）
        //对象有值则直接传值
        Optional<Employee> op = Optional.ofNullable(null);
        //System.out.println("employee = " + employee.get());
        //判断是否为空8
        /*
        if(op.isPresent()){
            Employee employee = op.get();
        }else{
            System.out.println("容器内为空");
        }
        */
        //没有值 则创建值
        Employee emp = op.orElse(new Employee(10, "张三", 18, 1000.00, Employee.Status.BUSY));
        System.out.println(emp);
        //函数式接口 可以返回任何形式的对象（具有功能）
        Employee em = op.orElseGet(() -> new Employee());
        System.out.println("em = " + em);
    }

    @Test
    public void test4(){
        Optional<Employee> op = Optional.ofNullable(new Employee(10, "张三", 18, 1000.00, Employee.Status.BUSY));
        Optional<String> str = op.map((e) -> e.getName());
        System.out.println("str = " + str);
        //flatMap不管返回什么 都是返回一个optional
        Optional<Object> o = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println("o.get() = " + o.get());
    }


    /**
     * 例题：
     *  男人不一定有女神 女神一定有名字
     *  获取男人对应的女神的名字
     */
    @Test
    public void test5(){
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println("godnessName = " + godnessName);
        Optional<NewMan> man2= Optional.ofNullable(null);
        String s = newGetGodnessName(man2);
        System.out.println("s = " + s);

    }

    public String getGodnessName(Man man){
       if (man.getGodness()!=null){
           return man.getGodness().getName();
       }else {
           return "无对应女神";
       }
    }

    public String newGetGodnessName(Optional<NewMan> man){
    return man.orElse(new NewMan())
            .getGodness()
            .orElse(new Godness("aaa"))
            .getName();
    }
}
