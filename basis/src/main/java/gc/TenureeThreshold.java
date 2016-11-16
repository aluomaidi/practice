package gc;

/**
 * Created by admin on 2016/11/2.
 * 1:  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
 * 2:  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:MaxTenuringThreshold=2 -XX:+UseSerialGC
 */
public class TenureeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold2();
    }
    public static void testTenuringThreshold() {
        byte[] data1, data2, data3;
        data1 = new byte[_1MB / 4];
        data2 = new byte[4 * _1MB];
        data3 = new byte[4 * _1MB];
        data3 = null;
        data3 = new byte[4 * _1MB];
    }
    public static void testTenuringThreshold2() {
        byte[] data1, data2;
        data1 = new byte[_1MB / 4];
        data2 = new byte[4 * _1MB];
        data2 = new byte[3 * _1MB];
        data2 = new byte[_1MB / 2];
        data2 = new byte[_1MB / 2];
//        data2 = new byte[_1MB * 4];
    }
}
