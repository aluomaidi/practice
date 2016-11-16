package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by admin on 2016/10/19.
 */
public class TUnsafe {
    static class Player {
        private final String name;
        public Player (String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Player player = new Player("maidi");
        Unsafe unsafe = getUnsafe();
        long offset = unsafe.objectFieldOffset(Player.class.getDeclaredField("name"));
        System.out.println(player.name);
        unsafe.putObject(player, offset, "kebo");
        System.out.println(player.name);
    }
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe)f.get(null);
    }
}
