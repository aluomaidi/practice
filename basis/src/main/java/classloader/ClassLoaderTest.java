package classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luozj on 2016/11/15.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
               String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                if (inputStream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] classSource = new byte[inputStream.available()];
                    inputStream.read(classSource);
                    return defineClass(name, classSource, 0, classSource.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Class myclass = classLoader.loadClass("classloader.ClassLoaderTest");
        Object myobj = myclass.newInstance();

        System.out.println(myobj.getClass());
        System.out.println(myclass.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(myobj instanceof ClassLoaderTest);
    }
}
