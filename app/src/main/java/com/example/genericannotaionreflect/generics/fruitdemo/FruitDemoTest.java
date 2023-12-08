package com.example.genericannotaionreflect.generics.fruitdemo;

public class FruitDemoTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * 不使用泛型
     */
    private static void test1() {
        James james = new James();
        Lucy lucy = new Lucy();
        FruitPlate fruitPlate = james.getPlate();
        james.addFruit(fruitPlate, new Apple());  //运行报错
        james.addFruit(fruitPlate, new Orange());  //运行不报错
        lucy.eat((Orange) fruitPlate.get());  //子类到父类需要强转
    }

    /**
     * 使用泛型
     */
    private static void test2() {
        James james = new James();
        Lucy lucy = new Lucy();
        AiFruitPlate<Orange> aiFruitPlate = james.getAiFruitPlate(); //AiFruitPlate可以承接泛型
        james.add(aiFruitPlate, new Orange());
        lucy.eat(aiFruitPlate.get());  //不需要强转
    }
}
