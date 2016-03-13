package basis.concurrent;

/**
 * Created by luozj on 16/3/13.
 */
public class LockObject {
    int value;
    public synchronized int getAndSet () {
        return ++value;
    }
    public synchronized void set (int _value) {
        value =_value;
    }
    public synchronized int get () {
        return value;
    }
}
