package basis.proxy;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/9/14:31
 */
public class StaticProxySubject implements Subject {

    Subject realSubject;

    public StaticProxySubject(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void doSomething() {
        this.beforeDoSomething();
        realSubject.doSomething();
        this.afterDoSomething();
    }

    public void beforeDoSomething(){
        System.out.println("before clean bedroom...");
    }

    public void afterDoSomething(){
        System.out.println("after clean bedroom...");
    }
}
