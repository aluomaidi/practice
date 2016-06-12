package thrift.hello;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

/**
 * Created by admin on 2016/6/3.
 */
public class HelloServiceServer {

    public static void main(String[] args) {
        try {
            nonblockingServer();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    private static void simpleServer () throws TTransportException {
        // Transport层提供了一个简单的网络读写抽象层
        TServerSocket serverTransport = new TServerSocket(9999);
        // Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作
        TProcessor processor = new HelloWorld.Processor(new HelloServiceImpl());
        TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
        System.out.println("Start server on port 9999...");
        server.serve();
    }

    private static void threadPoolServer () throws TTransportException {
        // Transport层提供了一个简单的网络读写抽象层
       TServerSocket serverSocket = new TServerSocket(9999);
        // Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作
        TProcessor processor = new HelloWorld.Processor(new HelloServiceImpl());
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverSocket).processor(processor));
        System.out.println("Start server on port 9999...");
        server.serve();
    }

    private static void nonblockingServer () throws TTransportException {
        // Transport层提供了一个简单的网络读写抽象层
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9999);
        // Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作
        TProcessor processor = new HelloWorld.Processor<>(new HelloServiceImpl());
        TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(processor));
        System.out.println("Start server on port 9999...");
        server.serve();
    }

    private static void nonblockingServerAsync () throws TTransportException {
        // Transport层提供了一个简单的网络读写抽象层
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9999);
        // Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作
        TProcessor processor = new HelloWorld.AsyncProcessor<>(new AsyncHelloServiceImpl());
        TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(processor));
        System.out.println("Start server on port 9999...");
        server.serve();
    }

}
