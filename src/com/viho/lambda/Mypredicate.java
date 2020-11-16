package com.viho.lambda;

/**
 * author viho
 *
 * @create 2020-10-27上午 10:53
 */
@FunctionalInterface
//修饰之后只能有一个抽象方法
public interface Mypredicate<T> {
    public boolean test(T t);
}
