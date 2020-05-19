package com.steer.jvm.objModel;

import org.openjdk.jol.info.ClassLayout;

public class LockTest {
    public static void main(String[] args) {
        Student student = new Student();
        //1.查看对象内部信息
        System.out.println(ClassLayout.parseInstance(student).toPrintable());
        synchronized (student){
            System.out.println("\n==============");
            System.out.println(ClassLayout.parseInstance(student).toPrintable());
        }
    }
}
