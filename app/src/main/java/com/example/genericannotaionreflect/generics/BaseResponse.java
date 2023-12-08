package com.example.genericannotaionreflect.generics;

class BaseResponse<T, K> {
    protected int code;
    protected T data;
    protected K msg;
}
