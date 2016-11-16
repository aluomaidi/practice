package OutOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/1.
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
