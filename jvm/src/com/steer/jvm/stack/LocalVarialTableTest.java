package com.steer.jvm.stack;

import java.util.Date;

/**
 * 栈帧中的本地变量表
 */
public class LocalVarialTableTest {
    public static void main(String[] args) {
        LocalVarialTableTest test = new LocalVarialTableTest();
        int num = 10;
        test.test1();
    }

    private void test1() {
        Date date = new Date();
        //匿名变量 不会出现在局部变量表中
        String name = "steer";
        String info = test2(date);
    }

    private String test2(Date dateP) {
        dateP = null;
        String name2 = "hehe";
        //局部变量表中基本存储单元是slot.double和float占用2个slot
        double weight = 133.00;
        char gender = '男';
        return dateP+name2;
    }

    private void test3(){
        int a = 0;
        {
            int b = 4;
            b = a +1;
        }
        //变量c占据着之前已经销毁的变量b占据的slot的位置
        int  c = a +1;
    }
}
