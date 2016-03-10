package basis.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @desc:
 * @author:luozj
 * @date:2016/3/9/14:47
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        Subject target = new RealSubject();
        InvocationHandler handler = new DynamicProxyHandler(target);
        Subject proxy = (Subject)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        proxy.doSomething();
        createProxyClassFile();
    }

    public static void createProxyClassFile()
    {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { Subject.class } );
        try
        {
           /* System.out.println(new File("/").getAbsolutePath());
            System.out.println(System.getProperty("user.dir"));
            System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
            System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
            System.out.println(DynamicProxyTest.class.getResource("/"));*/
//            FileOutputStream out = new FileOutputStream(name + ".class");
            FileOutputStream out = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+name + ".class");
            out.write(data);
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
