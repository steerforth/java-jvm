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
        System.out.println("a的值pre:"+a);
        a = 9;
        System.out.println("a的值after:"+a);
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
