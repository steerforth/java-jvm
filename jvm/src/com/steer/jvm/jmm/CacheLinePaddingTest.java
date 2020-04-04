package com.steer.jvm.jmm;

/**
 * 缓存行对齐
 *
 * disruptor框架  用的技术就是缓存行
 */
public class CacheLinePaddingTest {
    //64个字节一个缓存行
    private static class Padding{
        public volatile long p1,p2,p3,p4,p5,p6,p7;
    }

    private static class T extends Padding{
        /**
         * long占8个字节
         */
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        //很可能是相邻的CPU缓存存储
        arr[0] = new T();
        arr[1] = new T();
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("耗时:"+(System.nanoTime()-start)/1000_0000L);
    }
}
