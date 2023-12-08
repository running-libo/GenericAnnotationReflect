package com.example.genericannotaionreflect.generics.fruitdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能水果盘，实现水果盘接口
 * @param <T>
 */
public class AiFruitPlate<T> implements Plate<T> {
    private List<T> fruits = new ArrayList<>(6);


    @Override
    public void set(T t) {
        fruits.add(t);
    }

    @Override
    public T get() {
        int index = fruits.size() - 1;
        if (index >= 0) return fruits.get(index);
        return null;
    }
}
