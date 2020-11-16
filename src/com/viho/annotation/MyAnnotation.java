package com.viho.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * author viho
 * 创建一个自己的注解
 * @create 2020-11-16上午 9:55
 */
//添加元注解
    //TYPE_PARAMETER  可以注解类型
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
@Retention(RetentionPolicy.SOURCE)
//添加重复注解必须使用该注解 并指定该注解的容器注解
@Repeatable(MyAnntations.class)
public @interface MyAnnotation {
    String value() default "viho";

}
