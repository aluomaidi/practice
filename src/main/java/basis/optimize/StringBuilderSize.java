package basis.optimize;

/**
 * @desc:考虑StringBuilder初始化容量(字符char个数)，防止扩容，复制数组，提高性能,因为我们的ps_key+point一般大于16，所以适当增加初始容量，25左右差不多
 * @author:luozj
 * @date:2016/1/20/18:12
 */
public class StringBuilderSize {

    private static void stringJoin() {
        StringBuilder sb = new StringBuilder();
        // 生成每一个单元的测点key
        sb.append("100021").append("_").append("17")
                .append("_").append("11").append("_")
                .append("11").append("_").append("81023");
    }

    public static void main(String[] args) {
        StringBuilderSize.stringJoin();
    }
}
