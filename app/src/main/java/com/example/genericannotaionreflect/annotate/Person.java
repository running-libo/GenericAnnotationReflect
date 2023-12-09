package com.example.genericannotaionreflect.annotate;

public class Person {

    @MyAnnotation(name = "小王",age = 25,phone = "110")
    public void getInfo() {

    }

    public static void getAnnoValue() {
        try {
            MyAnnotation myAnnotation = Person.class.getMethod("getInfo").getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.name() + "  " + myAnnotation.age() + "   " + myAnnotation.phone());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
