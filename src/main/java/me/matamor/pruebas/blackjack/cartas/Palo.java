package me.matamor.pruebas.blackjack.cartas;

import me.matamor.pruebas.test.tiempo.colores.Color;
import me.matamor.pruebas.test.tiempo.colores.ColorBuilder;

public enum Palo implements Unicode {

    TREBOL("Trebol", '\u2667', Color.VERDE),
    DIAMANTE("Diamante", '\u2666', Color.AZUL),
    CORAZON("Corazon", '\u2665', Color.ROJO),
    ESPADA("Espada", '\u2660', Color.NEGRO);

    private final String nombre;
    private final char unicode;
    private final Color color;

    Palo(String nombre, char unicode, Color color) {
        this.nombre = nombre;
        this.unicode = unicode;
        this.color = color;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public char getUnicode() {
        return this.unicode;
    }

    public Color getColor() {
        return this.color;
    }

    public static void main(String[] args) {
        System.out.println(ColorBuilder.builder(Color.ROJO).build("\u2665"));
    }

}
