package com.ZHA.server.basic;

import java.lang.reflect.InvocationTargetException;

public class ReflactTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clz = Class.forName("com.ZHA.server.basic.iPhone");
        iPhone myphone = (iPhone)clz.getConstructor().newInstance();
        System.out.println(myphone.name);
    }

}
class iPhone{
    public iPhone(){

    }

    String name = "345";
}
