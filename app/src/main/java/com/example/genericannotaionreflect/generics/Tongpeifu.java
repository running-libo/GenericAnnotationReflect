package com.example.genericannotaionreflect.generics;

import com.example.genericannotaionreflect.generics.fruitdemo.Apple;
import com.example.genericannotaionreflect.generics.fruitdemo.Fruit;
import java.util.ArrayList;
import java.util.List;

public class Tongpeifu {

    public void test() {
        //List<Apple> 和 List<Fruit> 的公共父级是 List<?>
        List<Apple> apples = new ArrayList<>();
//        List<Fruit> fruits = apples;

        List<? extends Fruit> fruits1 = apples; //第一种方式承接

        List<? super Apple> fruits2 = apples; //第二种方式承接
    }
}
