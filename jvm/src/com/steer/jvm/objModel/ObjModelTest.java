package com.steer.jvm.objModel;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class ObjModelTest {
    public static void main(String[] args) {
        Student student = new Student();
        //1.查看对象内部信息
        System.out.println(ClassLayout.parseInstance(student).toPrintable());
        //2.查看对象外部信息
        System.out.println(GraphLayout.parseInstance(student).toPrintable());
        //3.查看对象占用空间总大小
        System.out.println("\n空间总大小:"+GraphLayout.parseInstance(student).totalSize());
    }
}
