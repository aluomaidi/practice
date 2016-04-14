package basis.concurrent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/12/17:03
 */
public abstract class PoolSizeCalculator {
    /**
     * 011
     * The sample queue size to calculate the size of a single {@link Runnable} element.
     * 012
     */
    private final int SAMPLE_QUEUE_SIZE = 1000;

    /**
     * 016
     * Accuracy of test run. It must finish within 20ms of the testTime otherwise we retry the test. This could be
     * 017
     * configurable.
     * 018
     */
    private final int EPSYLON = 20;

    /**
     * 022
     * Control variable for the CPU time investigation.
     * 023
     */
    private volatile boolean expired;

    /**
     * 027
     * Time (millis) of the test run in the CPU time calculation.
     * 028
     */
    private final long testtime = 3000;

    /**
     * 032
     * Calculates the boundaries of a thread pool for a given {@link Runnable}.
     * 033
     * <p/>
     * 034
     *
     * @param targetUtilization    035
     *                             the desired utilization of the CPUs (0 <= targetUtilization <= 1)
     *                             036
     * @param targetQueueSizeBytes 037
     *                             the desired maximum work queue size of the thread pool (bytes)
     *                             038
     */
    protected void calculateBoundaries(BigDecimal targetUtilization, BigDecimal targetQueueSizeBytes) {
        calculateOptimalCapacity(targetQueueSizeBytes);
        Runnable task = creatTask();
        start(task);
        start(task); // warm up phase
        long cputime = getCurrentThreadCPUTime();
        start(task); // test intervall
        cputime = getCurrentThreadCPUTime() - cputime;
        long waittime = (testtime * 1000000) - cputime;
        calculateOptimalThreadCount(cputime, waittime, targetUtilization);
    }

    private void calculateOptimalCapacity(BigDecimal targetQueueSizeBytes) {
        long mem = calculateMemoryUsage();
        BigDecimal queueCapacity = targetQueueSizeBytes.divide(new BigDecimal(mem), RoundingMode.HALF_UP);
        System.out.println("Target queue memory usage (bytes): " + targetQueueSizeBytes);
        System.out.println("createTask() produced " + creatTask().getClass().getName() + " which took " + mem
                + " bytes in a queue");
        System.out.println("Formula: " + targetQueueSizeBytes + " / " + mem);
        System.out.println("* Recommended queue capacity (bytes): " + queueCapacity);
    }

    /**
     * 062
     * Brian Goetz' optimal thread count formula, see 'Java Concurrency in Practice' (chapter 8.2)
     * 063
     * <p/>
     * 064
     *
     * @param cpu               065
     *                          cpu time consumed by considered task
     *                          066
     * @param wait              067
     *                          wait time of considered task
     *                          068
     * @param targetUtilization 069
     *                          target utilization of the system
     *                          070
     */
    private void calculateOptimalThreadCount(long cpu, long wait, BigDecimal targetUtilization) {
        BigDecimal waitTime = new BigDecimal(wait);
        BigDecimal computeTime = new BigDecimal(cpu);
        BigDecimal numberOfCPU = new BigDecimal(Runtime.getRuntime().availableProcessors());
        BigDecimal optimalthreadcount = numberOfCPU.multiply(targetUtilization).multiply(
                new BigDecimal(1).add(waitTime.divide(computeTime, RoundingMode.HALF_UP)));
        System.out.println("Number of CPU: " + numberOfCPU);
        System.out.println("Target utilization: " + targetUtilization);
        System.out.println("Elapsed time (nanos): " + (testtime * 1000000));
        System.out.println("Compute time (nanos): " + cpu);
        System.out.println("Wait time (nanos): " + wait);
        System.out.println("Formula: " + numberOfCPU + " * " + targetUtilization + " * (1 + " + waitTime + " / "
                + computeTime + ")");
        System.out.println("* Optimal thread count: " + optimalthreadcount);
    }

    /**
     * 088
     * Runs the {@link Runnable} over a period defined in {@link #testtime}. Based on Heinz Kabbutz' ideas
     * 089
     * (http://www.javaspecialists.eu/archive/Issue124.html).
     * 090
     * <p/>
     * 091
     *
     * @param task 092
     *             the runnable under investigation
     *             093
     */
    public void start(Runnable task) {
        long start = 0;
        int runs = 0;
        do {
            if (++runs > 5) {
                throw new IllegalStateException("Test not accurate");
            }
            expired = false;
            start = System.currentTimeMillis();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    expired = true;
                }
            }, testtime);
            while (!expired) {
                task.run();
            }
            start = System.currentTimeMillis() - start;
            timer.cancel();
        } while (Math.abs(start - testtime) > EPSYLON);
        collectGarbage(3);
    }

    private void collectGarbage(int times) {
        for (int i = 0; i < times; i++) {
            System.gc();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * 131
     * Calculates the memory usage of a single element in a work queue. Based on Heinz Kabbutz' ideas
     * 132
     * (http://www.javaspecialists.eu/archive/Issue029.html).
     * 133
     * <p/>
     * 134
     *
     * @return memory usage of a single {@link Runnable} element in the thread pools work queue
     * 135
     */
    public long calculateMemoryUsage() {
        BlockingQueue<Runnable> queue = createWorkQueue();
        for (int i = 0; i < SAMPLE_QUEUE_SIZE; i++) {
            queue.add(creatTask());
        }
        long mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        queue = null;
        collectGarbage(15);
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        queue = createWorkQueue();
        for (int i = 0; i < SAMPLE_QUEUE_SIZE; i++) {
            queue.add(creatTask());
        }
        collectGarbage(15);
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        return (mem1 - mem0) / SAMPLE_QUEUE_SIZE;
    }

    /**
     * 156
     * Create your runnable task here.
     * 157
     * <p/>
     * 158
     *
     * @return an instance of your runnable task under investigation
     * 159
     */
    protected abstract Runnable creatTask();

    /**
     * 163
     * Return an instance of the queue used in the thread pool.
     * 164
     * <p/>
     * 165
     *
     * @return queue instance
     * 166
     */
    protected abstract BlockingQueue<Runnable> createWorkQueue();

    /**
     * 170
     * Calculate current cpu time. Various frameworks may be used here, depending on the operating system in use. (e.g.
     * 171
     * http://www.hyperic.com/products/sigar). The more accurate the CPU time measurement, the more accurate the results
     * 172
     * for thread count boundaries.
     * 173
     * <p/>
     * 174
     *
     * @return current cpu time of current thread
     * 175
     */
    protected abstract long getCurrentThreadCPUTime();

}
