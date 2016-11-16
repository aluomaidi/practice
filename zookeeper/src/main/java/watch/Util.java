package watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by admin on 2016/8/31.
 */
public class Util {
   private static Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.toString());
        }
    };
    public static ZooKeeper getInstance (String address, int timeout, boolean needWatcher) throws IOException {
        return needWatcher ? new ZooKeeper(address, timeout, watcher) : new ZooKeeper(address, timeout, null);
    }
}
