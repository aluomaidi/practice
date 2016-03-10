package basis.proxy;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/9/14:26
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        Subject proxy = new StaticProxySubject(new RealSubject());
        proxy.doSomething();
    }
}
