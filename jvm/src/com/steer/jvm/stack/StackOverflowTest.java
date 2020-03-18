package com.steer.jvm.stack;

/**
 * 栈溢出测试
 */
public class StackOverflowTest {
    private static int count = 1;
    /**
     * jvm参数设置栈大小:-Xss2MB
     * Stackoverflow
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
