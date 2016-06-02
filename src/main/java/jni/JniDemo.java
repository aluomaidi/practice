package jni;

/**
 * Created by admin on 2016/5/19.
 */
public class JniDemo {
    static {
        System.loadLibrary("jnidemo");
    }

    public native int sum (int a, int b);
}
