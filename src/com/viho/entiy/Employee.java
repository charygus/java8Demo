package com.viho.entiy;

import jdk.net.SocketFlow;

import java.util.Objects;

/**
 * author viho
 *
 * @create 2020-10-27上午 10:02
 */
public class Employee {
    private int id;
    private  String name;
    private  int age;
    private  double salay;
    private Status Status;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalay() {
        return salay;
    }

    public void setSalay(double salay) {
        this.salay = salay;
    }



    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee(int id, String name, int age, double salay, Employee.Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salay = salay;
        Status = status;
    }
    
    public Employee(String name, int age, double salay) {
        this.name = name;
        this.age = age;
        this.salay = salay;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salay=" + salay +
                ", Status=" + Status +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Double.compare(employee.salay, salay) == 0 &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salay);
    }
    public enum Status {
        FREE,
        BUSY,
        VOCATION;
    }

    public Employee.Status getStatus() {
        return Status;
    }

    public void setStatus(Employee.Status status) {
        Status = status;
    }
}
