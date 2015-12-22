package nio.fileChannel;

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
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }

    public static void main(String[] args) {
        try {
            FileChannelTest.readFromFile("/Users/luozj/Downloads/test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

