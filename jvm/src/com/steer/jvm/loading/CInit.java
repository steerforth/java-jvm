package com.steer.jvm.loading;

/**
 * jclasslib插件
 * 类的加载过程
 * loading-linking-initialization
 */
public class CInit {
    static int a = 6;

    int b = 4;

    static {
        a = 9;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
