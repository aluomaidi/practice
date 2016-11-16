package OutOfMemory;

/**
 * Created by admin on 2016/11/1.
 */
public class StackOverFlow {
    private int stackLength = 1;
    public void stackLeak () {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + stackOverFlow.stackLength);
            throw e;
        }
    }
}
