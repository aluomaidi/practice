package concurrent;

/**
 * Created by admin on 2016/9/1.
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            boolean interrupted = false;
            @Override
            public void run() {
                while (!interrupted) {
                    System.out.println("running...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        interrupted = true;
                        System.out.println("interrupting...");
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();

    }
}
