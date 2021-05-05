package me.matamor.pruebas.lib;

public class Validate {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalStateException(message);
        }
    }

    public static void ifTrue(boolean codicion, String message) {
        if (codicion) {
            throw new IllegalStateException(message);
        }
    }

    public static void ifFalse(boolean condicion, String message) {
        if (!condicion) {
            throw new IllegalStateException(message);
        }
    }
}
