package nio.channel.filechannel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by luozj on 15/12/22.
 */
public class FileChannelTest {
    public static void readFromFile(String file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        /**
         * buffer三要素,capacity,limit,position
         * capacity:缓冲区的大小
         * limit:当前还可以向缓冲区写入多少数据或者是可以从缓冲区读取多少数据
         * position:缓冲区当前的指针位置（不论读写都从该位置开始）
         * **/

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);
        /**
         * flip方法：调用该方法会将limit设置为当前的position,而position设置为0，
         * 作用:你可以通过调用该方法来读取刚才写入的数据
         * **/
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }

    public static void main(String[] args) {
        try {
//            String path = "/Users/luozj/Downloads/test";
            String path = "E://test.log";
            FileChannelTest.readFromFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

