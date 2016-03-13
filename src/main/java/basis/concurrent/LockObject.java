package basis.concurrent;

/**
 * Created by luozj on 16/3/13.
 */
public class LockObject {
    public synchronized void print1 (int count) {
        int i = 0;
        while (i<count) {
            System.out.print(1);
            i++;
        }
    }

    public synchronized void print2 (int count) {
        int i = 0;
        while (i<count) {
            System.out.print(2);
            i++;
        }
    }
}
