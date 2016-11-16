package OutOfMemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by admin on 2016/11/1.
 */
public class DirectoryMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
