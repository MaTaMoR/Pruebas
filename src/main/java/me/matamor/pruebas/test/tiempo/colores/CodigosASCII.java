package me.matamor.pruebas.test.tiempo.colores;

public enum CodigosASCII {

    ROJO("1;31m"),
    AMARILLO("1;33m"),
    VERDE("1;32m"),
    AZUL("1;34m"),
    MAGENTA("1;35m");

    public static String OPEN_COLOR = "\u001B[";
    public static String CLOSE_COLOR = "\u001B[0m";

    private final String color;

    CodigosASCII(String color) {
        this.color = color;
    }

    public String con(Object object) {
        return OPEN_COLOR + this.color + object + CLOSE_COLOR;
    }
}
