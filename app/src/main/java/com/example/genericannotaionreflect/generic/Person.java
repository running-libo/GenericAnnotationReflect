package com.example.genericannotaionreflect.generic;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Person> method(List<Person> persons) {
        return new ArrayList<>(persons);
    }
}
