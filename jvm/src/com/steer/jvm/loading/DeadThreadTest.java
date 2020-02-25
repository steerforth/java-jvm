package com.steer.jvm.loading;

public class DeadThreadTest {
    /**
     * 同步加锁
     * 多线程加载类，只会加载一次
     * @param args
     */
    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName()+"开始");

            DeadThread deadThread = new DeadThread();

            System.out.println(Thread.currentThread().getName()+"结束");
        };

        new Thread(r,"线程1").start();

        new Thread(r,"线程2").start();
    }



}

class DeadThread {
    static {
        if (true){
            System.out.println(Thread.currentThread().getName()+"初始化当前类");
            while (true){

            }
        }
    }
}

