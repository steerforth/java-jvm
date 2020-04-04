package com.steer.jvm.stringtable;

import java.util.ArrayList;
import java.util.List;

public class StringTableTest {
    /**
     *配置JVM参数:-Xmx20m  -XX:-UseGCOverheadLimit
     * java.lang.OutOfMemoryError: Java heap space
     * 说明jdk8字符串常量池放在堆空间
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
//            list.add(String.valueOf(i).intern());
        }
    }
}
