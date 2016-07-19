package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/9/14:41
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object subject;

    public DynamicProxyHandler() {

    }

    public DynamicProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.beforeDoSomething();
        method.invoke(subject, args);
        this.afterDoSomething();
        return null;
    }

    public void beforeDoSomething(){
        System.out.println("before clean bedroom...");
    }

    public void afterDoSomething(){
        System.out.println("after clean bedroom...");
    }
}
