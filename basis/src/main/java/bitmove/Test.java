package bitmove;

/**
 * Created by admin on 2016/8/12.
 */
public class Test implements Runnable {

    @Override
    public void run() {
        System.out.println("======================");
    }
    public void start() {

    }
    public static void main(String[] args) {
      /*  long time =System.currentTimeMillis();
        System.out.println(time);
        System.out.println(time<<24);
        System.out.println(time<<24>>>8);
        System.out.println(Long.toBinaryString(time).length());
        System.out.println(Long.toBinaryString(time));
        System.out.println(Long.toBinaryString(time<<24));
        System.out.println(Long.toBinaryString(time<<24>>>8));*/
        int i=1;
        System.out.println(Integer.toBinaryString(i));
        i = i<<1;
        System.out.println(Integer.toBinaryString(i));
        i=i>>>1;
        System.out.println(Integer.toBinaryString(i));
        int j =-256;
        System.out.println(Integer.toBinaryString(j));
        j=j<<23;
        System.out.println(Integer.toBinaryString(j));
        System.out.println(j);
        j=-2;
        System.out.println(Integer.toBinaryString(j));
        System.out.println(j);
        j=j>>>1;
        System.out.println(Integer.toBinaryString(j));
        System.out.println(j);
        j=-2;
        j=j>>1;
        System.out.println(Integer.toBinaryString(j));
        System.out.println(j);
        new Test().start();
    }
}
