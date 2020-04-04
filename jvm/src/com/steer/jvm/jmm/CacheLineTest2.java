package com.steer.jvm.jmm;

/**
 *
 * CPU缓存系统中是以缓存行（cache line）为单位存储的
 * CPU缓存行占64个字节
 * 当多线程修改互相独立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享
 */
public class CacheLineTest2 {
    private static class T{
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
     *
     * 一个线程每改一次x，很可能都会通知另一个线程
     * 因为arr[0],arr[1]很可能是在CPU层面的一个缓存行里
     * 这样性能会下降
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
