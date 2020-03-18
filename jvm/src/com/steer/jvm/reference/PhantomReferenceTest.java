package com.steer.jvm.reference;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 虚引用:
 * 当垃圾回收器准备回收一个对象时，
 * 如果发现它还有虚引用，就会在回收对象的内存之前，
 * 把这个虚引用加入到与之关联的引用队列中。
 *
 * 应用:
 * Bytebuffer申请直接内存时，会有一个Cleaner
 */
public class PhantomReferenceTest {
    private static final int _500M = 1024*1024*1024;

    public static void main(String[] args) throws IOException {
        /**
         * 创建DirectByteBuffer时会创建Cleaner对象(extend PhantomReference)
         */
        ByteBuffer buffer = ByteBuffer.allocateDirect(_500M);
        System.out.println("分配完毕");
        System.in.read();
        System.out.println("开始释放");
        buffer = null;
        System.gc();
        System.in.read();
    }
}
