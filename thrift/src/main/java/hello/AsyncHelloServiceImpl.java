package hello;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by admin on 2016/6/12.
 */
public class AsyncHelloServiceImpl implements HelloWorld.AsyncIface {
    @Override
    public void getMessage(String id, byte type, AsyncMethodCallback resultHandler) throws TException {
        String result = "welcome to call HelloWorld service!";
        resultHandler.onComplete(result);
    }
}
