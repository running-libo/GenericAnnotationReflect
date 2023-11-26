package com.example.genericannotaionreflect.generic;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String userName;
    private String password;

    public List<Person> method(List<Person> persons) {
        return new ArrayList<>(persons);
    }
}
