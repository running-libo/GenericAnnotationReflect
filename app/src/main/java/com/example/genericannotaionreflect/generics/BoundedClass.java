package com.example.genericannotaionreflect.generics;

public class BoundedClass <T extends Comparable> {

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T min(T outter) {
        if (this.t.compareTo(outter) > 0) {
            return outter;
        } else {
            return this.t;
        }
    }

    public static void main(String[] args) {
        BoundedClass<String> boundedClass = new BoundedClass<>(); //只能传入实现了 Comparable 接口的类型
        boundedClass.setT("IOS");
        System.out.println(boundedClass.min("Android"));
    }
}
