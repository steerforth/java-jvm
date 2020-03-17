package com.steer.jvm.loading;

import java.util.Random;

public class Final {
    /**
     * final修饰的常量，编译期就可以确定，类不需要被加载
     */
    static class FinalOne{
        public static final int x = 6/3;
        static {
            System.out.println("FinalOne被加载了");
        }
    }

    /**
     * final修饰的变量，运行时才确定的值，类需要被加载
     */
    static class FinalTwo{
        static final int x = new Random().nextInt(100);
        static {
            System.out.println("FinalTwo被加载了");
        }
    }

    public static void main(String[] args) {
        System.out.println(FinalOne.x);
        System.out.println(FinalTwo.x);
    }


}
