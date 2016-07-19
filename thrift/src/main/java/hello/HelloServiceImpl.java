package hello;

import org.apache.thrift.TException;

/**
 * Created by admin on 2016/6/3.
 */
public class HelloServiceImpl implements HelloWorld.Iface {
    @Override
    public String getMessage(String id, byte type) throws TException {
        String result = "welcome to call HelloWorld service!";
        return result;
    }
}
