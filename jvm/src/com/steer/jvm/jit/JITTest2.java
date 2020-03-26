package com.steer.jvm.jit;

/**
 * 方法内联
 */
public class JITTest2 {
    /**
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining  查看所有内联的方法
     * -XX:CompileCommand=dontinline,*JITTest2.square 取消具体某个类某个方法的内联
     * @param args
     */
    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 500; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                x = square(9);
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\t%d\n",i,x,(end-start));
        }
    }

    /**
     * 内联:把方法内的代码拷贝，粘贴到调用者位置
     * 还能进行常量折叠的优化
     * @param i
     * @return
     */
    private static int square(final int i){
        return i*i;
    }
}
