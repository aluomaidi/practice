package gc;

/**
 * Created by admin on 2016/11/2.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails  -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
 */
public class PretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
    public static void testPretenureSizeThreshold() {
        byte [] allocation;
        allocation = new byte[4 * _1MB];
    }
}
