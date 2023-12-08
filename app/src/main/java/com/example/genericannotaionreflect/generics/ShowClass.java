package com.example.genericannotaionreflect.generics;

import com.example.genericannotaionreflect.generics.fruitdemo.Apple;
import com.example.genericannotaionreflect.generics.fruitdemo.Fruit;
import com.example.genericannotaionreflect.generics.fruitdemo.Person;

/**
 * 泛型类
 * @param <T>
 */
public class ShowClass <T> {

    /**
     * 定义普通方法
     */
    public void show1(T t) {
        System.out.println(t.toString());
    }

    /**
     * 定义泛型方法
     * @param e
     * @param <E>
     */
    public <E> void show2(E e) {
        System.out.println(e.toString());
    }

    public <T> void show3(T t) {
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        Person person = new Person();

        ShowClass<Fruit> showClass = new ShowClass<>();
        showClass.show1(apple);
//        showClass.show1(person); //编译器会报错，因为 ShowClass<Fruit> 已经限定类型

        showClass.show2(apple); // 可以放入，泛型方法 <E> 可以是任何非基本类型
        showClass.show2(person); // 可以放入，泛型方法 <E> 可以是任何非基本类型

        showClass.show3(apple); //可以放入，泛型方法 <T> 和泛型类中的 <T> 不是同一条 T，可以是任何非基本类型
        showClass.show3(person); //可以放入，泛型方法 <T> 和泛型类中的 <T> 不是同一条 T，可以是任何非基本类型

    }
}
