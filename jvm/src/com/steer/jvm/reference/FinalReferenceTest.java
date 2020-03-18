package com.steer.jvm.reference;

/**
 * 终结器引用：
 *
 * //FIXME
 */
public class FinalReferenceTest {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("FinalReferenceTest调用了finalize方法");
    }

    public static void main(String[] args) {
        FinalReferenceTest reference = new FinalReferenceTest();
        reference = null;

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
