package com.zhangsl.entity;

import java.util.ArrayList;

/**
 * Created by Zhangsl on 2018/3/14.
 */
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static boolean stringTest(){
        String  a = "a" + "b" + 1;
        String b = "ab1";
        return a==b;
    }

    public static void main(String[] args) {
        String a = new String("ab1");
        String b = "ab1";
        System.out.println(a == b);
    }
}
