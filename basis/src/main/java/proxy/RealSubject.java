package proxy;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/9/14:28
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("clean bedroom...");
    }
}
