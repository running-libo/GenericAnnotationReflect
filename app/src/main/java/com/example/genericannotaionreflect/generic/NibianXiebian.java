package com.example.genericannotaionreflect.generic;

import java.util.LinkedList;
import java.util.List;

/**
 * 泛型协变和逆变的例子
 */

class Animal {

}

class Dog extends Animal {}

class Cat extends Animal {}

public class NibianXiebian {

    public static void test() {
        //子类可以把引用赋值给父类，编译和运行通过
        Animal animalOne = new Cat();
        Animal animalTwo = new Dog();

        //如果一只猫是一只动物，那一群猫是一群动物吗？
        //编译通过
        Animal[] animals = new Cat[10];
        animals[0] = new Cat();
        // 下面这行代码会抛运行时异常
        animals[1] = new Dog();
        Animal animal = animals[0];
    }

    public static void xiebian() {
        List<Cat> cats = new LinkedList<>();
        // 编译器报错
//        List<Animal> animals = cats;

        List<? extends Animal> animals2 = cats;

        List<? extends Animal> animals = new LinkedList<Cat>();
        // 以下四行代码都不能编译通过
//         animals.add(new Dog());
//         animals.add(new Cat());
//         animals.add(new Animal());
//         animals.add(new Object());
        // 可以添加null，但没意义
        animals.add(null);
        // 可以安全地取出来
        Animal animal = animals.get(0);
    }

    public static void nibian() {
        // 下面这行代码编译不通过
        // List<? super Animal> animals = new LinkedList<Cat>();
        // 下面都是OK的写法
        // List<? super Animal> animals = new LinkedList<Object>();
        // List<? super Animal> animals = new LinkedList<Animal>();
        // 等价于上面一行的写法
        List<? super Animal> animals = new LinkedList<>();
        animals.add(new Cat());
        animals.add(new Dog());
        // 取出来一定是Object
        Object object = animals.get(0);

        // 这样写是OK的
        List<? super Cat> cats = new LinkedList<Animal>();
    }
}
