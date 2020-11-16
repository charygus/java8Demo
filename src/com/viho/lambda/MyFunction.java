package com.viho.lambda;

/**
 * author viho
 *
 * @create 2020-10-27下午 5:11
 */
@FunctionalInterface
public interface MyFunction<T> {
    public Integer getValue(Integer num);
}
