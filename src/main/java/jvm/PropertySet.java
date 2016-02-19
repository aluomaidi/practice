package jvm;

/**
 * @desc:
 * @author:luozj
 * @date:2016/2/19/8:37
 */
public class PropertySet {

    public static void main(String[] args)
    {
        System.out.println(args[0]);
        System.out.println(System.getProperty("test.property"));
    }
}
