package thrift.hello;

import org.apache.thrift.TException;

/**
 * Created by admin on 2016/6/3.
 */
public class HelloServiceImpl implements Hello.Iface {
    @Override
    public String helloString(String request) throws TException {
        System.out.println("request:"+request);
        return "hello world";
    }
}
