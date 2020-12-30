package com.example.demo2.interfaces;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}
