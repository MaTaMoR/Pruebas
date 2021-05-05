package me.matamor.pruebas.lib;

public class ThreadUtils {

    public static void sleep() {
        sleep(1000);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
