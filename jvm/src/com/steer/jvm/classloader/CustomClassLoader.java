package com.steer.jvm.classloader;

import java.io.FileNotFoundException;

/**
 * 自定义类加载器
 */
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null){
                throw new FileNotFoundException();
            }else {
                return defineClass(name, result,0, result.length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        return null;
    }
}
