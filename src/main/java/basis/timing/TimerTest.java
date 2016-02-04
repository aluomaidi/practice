package basis.timing;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc:
 * @author:luozj
 * @date:2016/2/4/12:09
 */
public class TimerTest {
    private static long start;

    public static void main(String[] args) throws Exception
    {

        TimerTask task1 = new TimerTask()
        {
            @Override
            public void run()
            {

                System.out.println("task1 invoked ! "
                        + (System.currentTimeMillis() - start));
                try
                {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        };
        TimerTask task2 = new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("task2 invoked ! "
                        + (System.currentTimeMillis() - start));
            }
        };
        Timer timer = new Timer("timeThread");
        start = System.currentTimeMillis();
        timer.schedule(task1, 1000);
        timer.schedule(task2, 3000);

    }
}
