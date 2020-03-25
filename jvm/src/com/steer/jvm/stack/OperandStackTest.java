package com.steer.jvm.stack;

/**
 * 操作数栈
 */
public class OperandStackTest {

    /**
     * 本地方法栈刚创建时，PC寄存器放的是0(指令地址)
     */
    public void add() {
        /**
         * 1.bipush 10; 将10放入到操作数栈中    PC寄存器更改值为2
         * 2.istore_1; 将10取出操作数栈，放入局部变量表LocalVariableTable index为1的位置
         * index为0默认放的是this变量
         * 3.iconst_4:把int常量放入操作数栈中
         * 4.istore_2：将4取出操作数栈，放入局部变量表LocalVariableTable index为1的位置
         */
        byte a = 10;
        int b = 4;
        /**
         * 1.iload_1;
         * 2.iload_2;
         * 从局部变量表中取出索引为1和2的变量，放到操作数栈
         * 3.iadd;执行引擎翻译为机器指令，交由CPU执行
         * 4.istore_3;将结果放入局部变量表index为3的位置
         */
        int c = a +b;
        //当前操作stack=2，栈的深度为2
    }

    public int getSum(){
        int m = 10;
        int n = 20;
        int k = m +n;
        return k;
    }

    public void getSumTest(){
        /**
         * aload_0;
         */
        //获取上一次栈帧保存的返回结果，并保存在操作数栈中
        int i = getSum();
        int j = 10;
    }

    /**
     * iinc指令，直接在局部变量slot上进行自增运算操作，不是在操作数栈上执行的
     * i++,先执行iload,再执行iincg
     * ++i,先执行iinc,再执行iload
     */
    public void a(){
        //1/
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        //2.
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        //3.
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        //4.
        int i9 = 10;
        int i10 = i9++ + ++i9;
    }
}
