package gc;

/**
 * Created by admin on 2016/11/2.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseG1GC
 */
public class YoungToOld {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        testAllocation();
    }
    public static void testAllocation() {
        byte[] data1, data2, data3, data4;
        data1 = new byte[2 * _1MB];
        data2 = new byte[2 * _1MB];
        data3 = new byte[2 * _1MB];
        data4 = new byte[4 * _1MB];
    }
}
