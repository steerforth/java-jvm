package com.steer.jvm.jit;

/**
 * JIT运行期优化
 * 逃逸分析
 */
public class JITTest {
    /**
     * JVM将执行状态分为5个层次:
     * 0层：解释执行
     * 1层: 使用C1即时编译器执行(不带profiling)
     * 2层: 使用C1即时编译器执行(带基本的profiling)
     * 3层: 使用C1即时编译器执行(带完全的profiling)
     * 4层: 使用C2即时编译器执行
     *
     *
     * JVM参数:-XX:-DoEscapeAnalysis 关闭逃逸分析
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                new Object();
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\n",i,(end-start));
        }
    }
}
