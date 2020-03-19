package com.steer.jvm.gc;

import java.io.IOException;
import java.util.ArrayList;

public class GcAnalyzeTest {
    private static final int _512KB = 512*1024;
    private static final int _1M = 1024*1024;
    private static final int _6M = 6*1024*1024;
    private static final int _7M = 7*1024*1024;
    private static final int _8M = 8*1024*1024;

    /**
     * -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
     * 串行回收器：新生代使用复制算法，老年代使用标记-整理算法
     * 年轻代设置10M
     * @param args
     */
    public static void main(String[] args) {
//        test1();
        test2();
//        test3();
//        test4();
//        test5();
    }

    /**
     * 新分配的内存会导致eden space满
     * 触发新生代的垃圾回收
     */
    private static void test1(){
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_7M]);
    }

    /**
     * 新分配的内存会导致eden space满
     * 触发新生代的垃圾回收
     */
    private static void test2(){
        ArrayList<byte[]> list = new ArrayList<>();
        //触发垃圾回收
        list.add(new byte[_7M]);
        list.add(new byte[_512KB]);
        //eden不够用了，触发第2次垃圾回收。 一部分对象晋级到老年代。
        list.add(new byte[_512KB]);
    }

    /**
     * 大内存的申请
     * 申请的内存大于新生代剩余的空间
     * 不进行垃圾回收，直接晋级为老年代
     */
    private static void test3(){
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_8M]);
    }

    /**
     *
     * 第二次申请空间，新生代，老年代空间都不够用了，则OOM
     * 在这之前会进行一次自救，进行一次GC和Full GC，发现还是不够用，则OOM
     */
    private static void test4(){
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_8M]);
        list.add(new byte[_8M]);
    }

    /**
     * 线程内OOM,不会造成主线程退出
     */
    private static void test5(){
        new Thread(()->{
            ArrayList<byte[]> list = new ArrayList<>();
            list.add(new byte[_8M]);
            list.add(new byte[_8M]);
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 无任何对象，默认情况下各区占用情况
     *
     * Heap
     *  def new generation   total 9216K, used 2736K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  33% used [0x00000000fec00000, 0x00000000feeac2e0, 0x00000000ff400000)
     *   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     *  tenured generation   total 10240K, used 0K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,   0% used [0x00000000ff600000, 0x00000000ff600000, 0x00000000ff600200, 0x0000000100000000)
     *  Metaspace       used 3312K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 355K, capacity 392K, committed 512K, reserved 1048576K
     */
}
