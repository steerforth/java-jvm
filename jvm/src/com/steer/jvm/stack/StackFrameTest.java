package com.steer.jvm.stack;

public class StackFrameTest {
    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        System.out.println("开始运行方法A");
        methodB();
        System.out.println("方法A运行结束");
    }

    private static void methodB() {
        System.out.println("开始运行方法B");
        methodC();
        System.out.println("方法B运行结束");
    }

    private static void methodC() {
        System.out.println("开始运行方法C");
    }
}
