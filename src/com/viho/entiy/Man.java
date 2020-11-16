package com.viho.entiy;

/**
 * author viho
 *
 * @create 2020-11-13上午 10:22
 */
public class Man {
    private Godness godness;

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }


    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
