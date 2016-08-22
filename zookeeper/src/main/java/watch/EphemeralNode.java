package watch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by admin on 2016/8/17.
 */
public class EphemeralNode {
    public static void main(String[] args) throws KeeperException, InterruptedException {
        try {
            ZooKeeper zk = new ZooKeeper("192.168.4.79:2181", 3000, null);
//            zk.create("/locks",new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.create("/locks/lock_w_",null,ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zk.create("/locks/lock_r_",null,ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zk.create("/locks/lock_w_",null,ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {

                    }
                }
            });
            thread.start();
            thread.join();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
