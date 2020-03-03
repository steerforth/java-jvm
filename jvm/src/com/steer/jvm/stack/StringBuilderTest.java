package com.steer.jvm.stack;

/**
 * 方法中定义的局部变量是否线程安全
 */
public class StringBuilderTest {

    public static void method1() {
        //线程安全
        StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");
    }

    //变量为传入的参数，可能是线程共享参数，线程不安全
    public static void method2(StringBuilder builder) {
        builder.append("a");
        builder.append("b");
    }

    //有返回值，可能被其他线程调用
    public static StringBuilder method3() {
        //线程不安全
        StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append("b");
        return builder;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        new Thread(()->{
            builder.append("3");
            builder.append("4");
        }).start();

        method2(builder);

        System.out.println(builder.toString());
    }

}
