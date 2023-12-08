package com.example.genericannotaionreflect.generics;

/**
 * 多类型参数
 */
interface MultipleGeneric<K, V> {
    public K getKey();

    public V getValue();
}

public class ImplMultipleGeneric<K,V> implements MultipleGeneric<K,V> {
    private K key;
    private V value;

    public ImplMultipleGeneric(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
        MultipleGeneric<String, Integer> m1 = new ImplMultipleGeneric<>("lili", 18);
        System.out.println("key:" + m1.getKey() + ", value:" + m1.getValue());

        MultipleGeneric<String, String> m2 = new ImplMultipleGeneric<>("name", "sign");
        System.out.println("key:" + m2.getKey() + ", value:" + m2.getValue());
    }
}
