package com.example.genericannotaionreflect.generics.fruitdemo;

public class James extends Person {

    public FruitPlate getPlate() {
        return new FruitPlate();
    }

    public AiFruitPlate getAiFruitPlate() {
        return new AiFruitPlate();
    }

    public void addFruit(FruitPlate fruitPlate, Fruit fruit) {
        fruitPlate.set(fruit);
    }

    public void add(AiFruitPlate<Orange> aiFruitPlate, Orange orange) {
        aiFruitPlate.set(orange);
    }

}
