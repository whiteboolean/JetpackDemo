package com.example.jetpackdemo.day1203_2021_intent_service;

import android.util.Log;

public class A {


    private static String a = getA();

    private static String b = getB();

    private String c = getC();


    public static String getA() {
        System.out.println("getA");
        return "saf";
    }

    public static String getB() {
        System.out.println("getBBB");
        return "B";
    }


    public static void getAAA(){
//        A a = new A();
        a.toString();
        System.out.println("getAAA");
    }


    public String  getC(){
        System.out.println("CCCC");
        return "ccc";
    }

}
