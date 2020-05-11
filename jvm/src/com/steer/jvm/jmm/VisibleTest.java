package com.steer.jvm.jmm;

import java.io.IOException;

/**
 * 可见性测试
 */
public class VisibleTest {
//    private static void boolean flag = false;
    private static boolean flag = false;

    public static void main(String[] args) {
        test1();
    }

    /**
     * 线程退不出，JIT会进行优化，flag一直是在工作内存中的变量，没有读取主内存中的数据
     */
    private static void test1() {
        new Thread(()->{
            System.out.println("start");
            while (!flag){
                for (int i = 0; i < 10; i++) {
                    i++;
                }
            }
            System.out.println("end");
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 线程会退出，因为System.out.println底层有synchronized关键字，
     * 会从主内存中读取变量
     */
    private static void test2() {
        new Thread(()->{
            System.out.println("start");
            while (!flag){
                System.out.println("do");
            }
            System.out.println("end");
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
