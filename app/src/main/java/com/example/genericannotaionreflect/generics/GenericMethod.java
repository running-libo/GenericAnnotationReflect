package com.example.genericannotaionreflect.generics;

public class GenericMethod {

    public <T> T genericMethod(T ...a) {
        return a[a.length-1];
    }

    public static void main(String[] args) {
        //声明调用genericMethod方法的泛型为String类型
        String name = new GenericMethod().<String>genericMethod("xiaoming", "xiaohong", "xiaowang");
        int num = new GenericMethod().genericMethod(1, 2, 3);
        System.out.println(name);
        System.out.println(num);
    }
}
