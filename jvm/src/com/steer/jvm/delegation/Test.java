package com.steer.jvm.delegation;

/**
 * 双亲委派机制
 */
public class Test {
    /**
     * 虽然String和自定义包名java.lang一样
     * 实际执行的还是系统的String
     * 双亲委派机制
     * @param args
     */
    public static void main(String[] args) {
        String s = new String();
        System.out.println(s);
    }
}
