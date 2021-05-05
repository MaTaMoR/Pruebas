package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.test.tiempo.colores.Color;
import me.matamor.pruebas.test.tiempo.colores.ColorBuilder;

public enum Palo implements Unicode {

    TREBOL("Trebol", '\u2667'),
    DIAMANTE("Diamante", '\u2662'),
    CORAZON("Corazon", '\u2665'),
    ESPADA("Espada", '\u2660');

    private final String nombre;
    private final char unicode;

    Palo(String nombre, char unicode) {
        this.nombre = nombre;
        this.unicode = unicode;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public char getUnicode() {
        return this.unicode;
    }

    public static void main(String[] args) {
        System.out.println(ColorBuilder.builder(Color.ROJO).build("\u2665"));
    }

}
