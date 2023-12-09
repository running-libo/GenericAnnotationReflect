package com.example.genericannotaionreflect.reflactdemo;

public class FatherClass {

    protected String mFatherName;
    private int mFatherAge;

    public FatherClass() {
    }

    public FatherClass(String mFatherName, int mFatherAge) {
        this.mFatherName = mFatherName;
        this.mFatherAge = mFatherAge;
    }

    public String getmFatherName() {
        return mFatherName;
    }

    public int getmFatherAge() {
        return mFatherAge;
    }
}
