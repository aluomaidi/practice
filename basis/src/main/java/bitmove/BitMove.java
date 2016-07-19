package bitmove;

/**
 * Created by admin on 2016/6/1.
 */
public class BitMove {
    public static void main(String[] args) {
        int i = 2;
        int j = -1;
        int k = -2; // 负数是补码
        System.out.println(i<<1);
        System.out.println(i>>1);
        System.out.println(i>>>1);
        System.out.println(j>>1);
        System.out.println(j<<1);
        System.out.println(j>>>1);
        System.out.println(k>>1);
        System.out.println(k<<1);
        System.out.println(k>>>1);
    }
}
