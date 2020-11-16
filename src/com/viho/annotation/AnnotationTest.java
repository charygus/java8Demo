package com.viho.annotation;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * author viho
 * 重复注解和类型注解
 * @create 2020-11-16上午 9:53
 */
public class AnnotationTest {
    //预防空指针异常  结合check framework
    private  Object obj = null ;
@MyAnnotation("world")
@MyAnnotation("hello")
    public void  show(@MyAnnotation("abc") String str){
    System.out.println("show");
    }

    @Test
    public void test1() throws NoSuchMethodException {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] ma = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : ma) {
            System.out.println("myAnnotation.value() = " + myAnnotation.value());
        }
    }
}
