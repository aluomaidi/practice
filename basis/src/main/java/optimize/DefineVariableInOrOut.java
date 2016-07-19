package optimize;

/**
 * @desc:测试变量声明在循环内部和外部的区别（测试结果，性能差别不大）
 * @author:luozj
 * @date:2016/1/20/17:19
 */
public class DefineVariableInOrOut {
    public static void outside(long size) {
        Object obj;
        for(int i=0; i <  size; i++){
            obj = new Object();
        }
    }

    public static void inside(long size) {
        for(int i=0; i <  size; i++){
            Object obj = new Object();
        }
    }

    public static void main(String[] args) {
        long size = 1000000000L;
        long start = System.currentTimeMillis();
//        DefineVariableInOrOut.outside(size);
        DefineVariableInOrOut.inside(size);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }
}
