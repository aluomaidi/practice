package basis.timing;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @author:luozj
 * @date:2016/2/4/14:50
 */
public class ScheduledExecutorServiceTest {
    private static long start;
    public static void main(String[] args) throws InterruptedException
    {

        final TimerTask task1 = new TimerTask()
        {

            @Override
            public void run()
            {
                 System.out.println("task1 invoked ! "
                        + (System.currentTimeMillis() - start));
                try
                {
                // 这里休眠的是TimerThread,执行线程，而不是当前TimerTask，
                // 因为TimerTask只是普通对象不是线程
                    Thread.sleep(3000);
                    System.out.println("threadName:"+Thread.currentThread().getName());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        final TimerTask task2 = new TimerTask()
        {

            @Override
            public void run()
            {
                System.out.println("task2 invoked!" + "---" +"threadName:"+Thread.currentThread().getName());
                System.out.println();
            }
        };

        start = System.currentTimeMillis();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.schedule(task1, 100, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(task2, 0 , 1000, TimeUnit.MILLISECONDS);

    }
}
