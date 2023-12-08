package com.example.genericannotaionreflect.generics;

import java.util.ArrayList;
import java.util.List;

public class OtherTest {

    public void test() {
        //泛型擦除判断类型
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();

        System.out.println(l1.getClass() == l2.getClass());
    }
}
