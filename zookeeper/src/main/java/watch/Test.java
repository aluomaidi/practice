package watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by admin on 2016/8/31.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = Util.getInstance("uucun-luozj:2181", 30000, true);
    }
}
