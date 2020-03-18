package com.steer.jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用: 描述一些还有用但并非必须的对象
 * JVM在分配空间时，若果Heap空间不足，就会进行相应的GC，
 * 但是这次GC并不会收集软引用关联的对象，
 * 但是在JVM发现就算进行了一次回收后还是不足（Allocation Failure），
 * JVM会尝试第二次GC，回收软引用关联的对象。
 *
 * 应用:图片缓存，网页缓存
 */
public class SoftReferenceTest {
    private static final int _4M = 4*1024*1024;

    /**
     * 配置JVM参数:-Xmx20m -XX:+PrintGCDetails -verbose:gc
     * @param args
     */
    public static void main(String[] args) {
//        soft();
        softQueue();
    }

    private static void soft() {
        //list-->SoftRegerence-->byte[]

        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> reference = new SoftReference<>(new byte[_4M]);
            System.out.println(reference.get());
            list.add(reference);
            System.out.println("大小:"+list.size());
            System.out.println("list0："+list.get(0).get());
        }
        System.out.println("循环结束:"+list.size());
        for (SoftReference<byte[]> re:list){
            System.out.println(re.get());
        }
    }


    private static void softQueue() {
        ReferenceQueue queue = new ReferenceQueue();
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> reference = new SoftReference<>(new byte[_4M],queue);
            System.out.println(reference.get());
            list.add(reference);
            System.out.println("大小:"+list.size());
            System.out.println("list0："+list.get(0).get());
        }
        System.out.println("循环结束:"+list.size());

        Reference<? extends byte[]> ref = queue.poll();

        while (ref != null){
            //手动移除SoftRegerence对象
            list.remove(ref);
            ref = queue.poll();
        }
        for (SoftReference<byte[]> re:list){
            System.out.println(re.get());
        }
    }
}
