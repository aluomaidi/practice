package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2016/9/27.
 */
public class CASTest {
    static AtomicInteger value = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                value.addAndGet(1);
            }
        });
        thread.start();
        value.addAndGet(1);

    }
}
