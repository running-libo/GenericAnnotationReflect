package com.example.genericannotaionreflect.generics;

interface Generitor<T> {
    T next();
}

public class Generitorimpl<T> implements Generitor<T> {

    @Override
    public T next() {
        return null;
    }
}

class Generitorimpl2<T> implements Generitor<String> {

    @Override
    public String next() {
        return null;
    }
}
