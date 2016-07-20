package watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/7/20.
 */
public class ClientTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        final AsyncCallback.VoidCallback voidCallback = new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println("path:"+path+";ctx:"+ctx+";rc:"+rc);
            }
        };
        final AsyncCallback.StatCallback statCallback = new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println("path:"+path+";stat:"+stat+";ctx:"+ctx+";rc:"+rc);
            }
        };
        final ZooKeeper zk = new ZooKeeper("192.168.13.98:2181", 3000, null);
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
                zk.exists("/zk_test", this, statCallback, "exists");
            }
        };

//          zk.addAuthInfo("ip", "192.168.13.98".getBytes("utf-8"));
        zk.exists("/zk_test", watcher, statCallback, "exists");
//        zk.create("/zk_test","zk_test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
//        thread.join();
//        zk.exists("/zk_test", watcher, statCallback, "exists");
//        zk.delete("/zk_test", -1,voidCallback , "delete");
//        zk.exists("/zk_test", watcher, statCallback, "exists");
    }
}
