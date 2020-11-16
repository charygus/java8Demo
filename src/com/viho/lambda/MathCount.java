package com.viho.lambda;

/**
 * author viho
 *
 * @create 2020-10-27下午 10:20
 */
@FunctionalInterface
public interface MathCount<T,R> {
    public R count(T t1,T t2);
}
