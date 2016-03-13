package basis.concurrent;

/**
 * Created by luozj on 16/3/13.
 */
public class SynchronizedTest {
    // 锁是真是类对象的，所以两个线程操作一个类的两个实例依然是互斥的

    public static void main(String[] args) {
        final LockObject obj1 = new LockObject();
        final LockObject obj2 = new LockObject();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                int i =1;
                while(i<10){
                    obj1.getAndSet();
                    System.out.println(obj1.get());
                    i++;
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                int i =1;
                while(i<10){
                    obj2.set(-1);
                    System.out.println(obj2.get());
                    i++;
                }

            }
        });
        thread2.start();
    }

}
