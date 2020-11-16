package com.viho.entiy;

/**
 * author viho
 *
 * @create 2020-11-13上午 10:23
 */
public class Godness {
    private String name;

    public Godness(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Godness{" +
                "name='" + name + '\'' +
                '}';
    }
}
