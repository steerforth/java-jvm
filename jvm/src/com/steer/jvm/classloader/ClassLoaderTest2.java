package com.steer.jvm.classloader;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest2 {
    public static void main(String[] args) {
        System.out.println("*****启动类加载器*****");
        /**
         * 加载的路径文件
         */
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url :urLs){
            System.out.println(url.getPath());
        }

        /**
         * 用类验证
         */
        ClassLoader classLoader = Provider.class.getClassLoader();
        //null
        System.out.println(classLoader);


        System.out.println("*****扩展类加载器*****");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(":")){
            System.out.println(path);
        }

        ClassLoader loader = CurveDB.class.getClassLoader();
        System.out.println(loader);
    }
}
