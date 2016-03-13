package basis.concurrent;

/**
 * Created by luozj on 16/3/13.
 */
public class SynchronizedTest {
    // 锁是真是实例对象的，所以两个线程操作一个类的两个实例是并发的

    public static void main(String[] args) {
        final LockObject obj1 = new LockObject();
        final LockObject obj2 = new LockObject();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                    obj1.print1(1000);
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                    obj2.print2(1000);
            }
        });
        thread2.start();
    }

}
