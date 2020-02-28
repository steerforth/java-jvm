package com.steer.jvm.stack;

public class StackOverflowTest {
    private static int count = 1;
    /**
     * 设置栈大小:-Xss2MB
     * Stackoverflow
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
