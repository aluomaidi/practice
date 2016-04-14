package basis.concurrent;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/12/17:08
 */
public class MyPoolSizeCalculator extends PoolSizeCalculator {
    public static void main(String[] args) throws InterruptedException,
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException {
        MyPoolSizeCalculator calculator = new MyPoolSizeCalculator();
        calculator.calculateBoundaries(new BigDecimal(1.0),
                new BigDecimal(100000));
    }

    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }

    protected Runnable creatTask() {
      /*  ThreadPoolExecutor pool =
        new ThreadPoolExecutor(6, 6,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(2500));
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());*/

//        return new AsynchronousTask(0, "IO", 1000000);
        return null;
    }

    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingQueue();
    }

}
