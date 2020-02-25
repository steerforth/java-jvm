package com.steer.jvm.classloader;

/**
 * BootstrapClassLoader用C/C++编写
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        /**
         * 获取系统类加载器
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@1f89ab83
        System.out.println(extClassLoader);

        ClassLoader bootstrapClassloader = extClassLoader.getParent();
        //null
        System.out.println(bootstrapClassloader);

        /**
         * 用户自定义类，默认使用系统类加载器进行在家
         */
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        /**
         * 系统核心类库都是BootstrapClassLoader
         */
        ClassLoader loader = String.class.getClassLoader();
        //null
        System.out.println(loader);

    }
}
