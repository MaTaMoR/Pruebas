package me.matamor.pruebas.blackjack;

import java.util.Arrays;

public class Baraja {

    private static final Carta[] cartas;

    static {
        Palo[] palos = Palo.values();
        Tipo[] tipos = Tipo.values();

        cartas = new Carta[palos.length * tipos.length];

        int contador = 0;

        for (Palo palo : palos) {
            for (Tipo tipo : tipos) {
                cartas[contador++] = new Carta(palo, tipo);
            }
        }
    }

    public static Carta[] getCartas() {
        return cartas.clone();
    }

    public static Mazo nuevoMazo() {
        return new Mazo(Arrays.asList(getCartas()));
    }
}
