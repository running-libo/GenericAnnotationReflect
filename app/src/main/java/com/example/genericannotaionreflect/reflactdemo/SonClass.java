package com.example.genericannotaionreflect.reflactdemo;

import androidx.annotation.NonNull;

import com.example.genericannotaionreflect.retrofit.GET;

public class SonClass extends FatherClass {

    private String mSonName;
    protected int mSonAge;
    public String mSonBirthday;

    public SonClass() {
    }

    @GET("https:\\www.baidu.com")
    private <T,K> String toStr(T t, K k) {
        return t.toString() + k.toString();
    }

    public String getmSonName() {
        return mSonName;
    }

    public void setmSonName(String mSonName) {
        this.mSonName = mSonName;
    }

}
