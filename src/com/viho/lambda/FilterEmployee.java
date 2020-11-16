package com.viho.lambda;

import com.viho.entiy.Employee;

/**
 * author viho
 *
 * @create 2020-10-27上午 10:54
 */
//实现Mypredicate接口
public class FilterEmployee implements Mypredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
