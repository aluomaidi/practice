package thrift.hello;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

import java.io.IOException;

/**
 * Created by admin on 2016/6/3.
 */
public class HelloServiceClient {
    public static void main(String[] args) {
        try {
            nonblockingClient();
        } catch (TException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void client () throws TException {
        // 设置调用的服务地址为本地，端口为 7911
        TTransport transport = new TSocket("localhost", 9999);
        transport.open();
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorld.Client client = new HelloWorld.Client(protocol);
        // 调用服务的 getMessage 方法
        String response = client.getMessage("1", (byte)1);
        System.out.println("response:" + response);
        transport.close();
    }
    private static void nonblockingClient () throws TException, IOException {
        // 设置调用的服务地址为本地，端口为 7911
//        TNonblockingTransport transport = new TNonblockingSocket("localhost", 9999);
//        transport.startConnect();
        TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 9999));
        transport.open();
        // 设置传输协议为 TBinaryProtocol
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorld.Client client = new HelloWorld.Client(protocol);
        // 调用服务的 getMessage方法
        String response = client.getMessage("1", (byte) 1);
        System.out.println("response:" + response);
        transport.close();
    }
    private static void asyncClient () throws TException, IOException {
        // 设置调用的服务地址为本地，端口为 7911
        TNonblockingTransport transport = new TNonblockingSocket("localhost", 9999);
        // 设置传输协议为 TBinaryProtocol
        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        HelloWorld.AsyncClient client = new HelloWorld.AsyncClient(protocolFactory, new TAsyncClientManager(), transport);
        // 调用服务的 getMessage方法
        client.getMessage("1", (byte) 1, new AsyncMethodCallback() {
            @Override
            public void onComplete(Object response) {
                System.out.println("response:" + response);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
        transport.close();
    }
}
