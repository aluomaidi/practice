package OutOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/1.
 */
public class ConstantPoolOOM {
    public static void main(String[] args) {
        // jdk1.6中会很快内存溢出，1.7中永久带实现不同
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
