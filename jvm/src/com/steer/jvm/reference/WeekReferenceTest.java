package com.steer.jvm.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用:非必须对象的，他的强度比软引用更弱一些
 * 被弱引用关联的对象，在垃圾回收时，
 * 如果这个对象只被弱引用关联（没有任何强引用关联他）
 * 那么这个对象就会被回收。
 *
 * 应用:WeakHashMap,ThreadLocal
 */
public class WeekReferenceTest {
    private static final int _4M = 4*1024*1024;

    /**
     * 配置JVM参数:-Xmx20m -XX:+PrintGCDetails -verbose:gc
     * @param args
     */
    public static void main(String[] args) {
        weak();
    }

    private static void weak() {
        //list-->WeakReference-->byte[]
        //fullgc会把弱引用对象回收
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("开始分配内存...");
            WeakReference<byte[]> reference = new WeakReference<>(new byte[_4M]);
            System.out.println("分配内存结束...");
            list.add(reference);
            for (WeakReference<byte[]> re:list){
                System.out.println(i+"==="+re.get());
            }
        }
        System.out.println("循环结束:"+list.size());
    }

}
