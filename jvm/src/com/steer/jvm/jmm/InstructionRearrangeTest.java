package com.steer.jvm.jmm;

/**
 * JVM多线程优化之:
 * 指令重排序
 *
 * 解决:volatile修饰，防止指令重排
 * 懒加载double-checked locking模式
 */
public class InstructionRearrangeTest {
    private static int x = 0,y =0;
    private static int a = 0,b =0;

    /**
     * 值的可能情况:
     * a=1,x=0;b=1,y=1
     * a=1,b=1,x=1,y=1
     * b=1,y=0,a=1,x=1
     *
     * 指令重排会造成:
     * x=0,y=0,a=1,b=1
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;){
            i++;
            x =0 ;y =0;
            a =0 ;b =0;
            Thread one = new Thread(()->{
               a = 1;
               x = b;
            });
            Thread two = new Thread(()->{
                b = 1;
                y = a;
            });

            one.start();
            two.start();

            one.join();
            two.join();

            String result = "第"+i+"次 （"+x+","+y+")";
            if ( x == 0 && y == 0){
                //第3087300次 （0,0)
                System.err.println(result);
            }
        }
    }
}
