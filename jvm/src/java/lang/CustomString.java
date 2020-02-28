package java.lang;

public class CustomString {
    /**
     * 防止核心API被篡改，会执行不成功
     * 沙箱安全机制
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("111");
    }
}
