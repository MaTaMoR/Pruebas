package me.matamor.pruebas.blackjack.cartas;

import me.matamor.pruebas.test.tiempo.colores.Color;
import me.matamor.pruebas.test.tiempo.colores.ColorBuilder;

public enum Tipo {

    AS("A", 11),
    DOS("2", 2),
    TRES("3", 3),
    CUATRO("4", 4),
    CINCO("5", 5),
    SEIS("6", 6),
    SIETE("7", 7),
    OCHO("8",8),
    NUEVE("9", 9),
    DIEZ("10", 10),
    JOTA("J", 10),
    DAMA("Q", 10),
    REY("K", 10);

    private final String nombre;
    private final int valor;

    Tipo(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getValor() {
        return this.valor;
    }

    public static void main(String[] args) {
        System.out.println(ColorBuilder.builder(Color.ROJO).build("\u1F0A1"));
    }
}
