package timing;

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

                /*System.out.println("task1 invoked ! "
                        + (System.currentTimeMillis() - start));
                try
                {
                // 这里休眠的是TimerThread,执行线程，而不是当前TimerTask，
                // 因为TimerTask只是普通对象不是线程
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }*/
                int size = Integer.MAX_VALUE>>1000;
                for (int i=1;i<size;i++) {
                    System.out.println(i);
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
      /*  //定时器只是实现runnable接口，并不是线程
        task1.run();
        task2.run();*/
       /*    //调用run方法只是调用普通成员方法，并没有启动线程
        new Thread(task1).run();
        new Thread(task2).run();*/
        //这样调用才会启动线程
       /* new Thread(task1).start();
        new Thread(task2).start();*/


    }
}
