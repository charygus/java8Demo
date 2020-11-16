package com.viho.entiy;

import java.util.Optional;

/**
 * author viho
 *
 * @create 2020-11-13上午 11:09
 */
public class NewMan {
    private Optional<Godness> godness = Optional.empty();

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
