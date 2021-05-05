package me.matamor.pruebas.blackjack;

import java.util.Arrays;

public class Baraja {

    private final Carta[] cartas;

    public Baraja() {
        Palo[] palos = Palo.values();
        Tipo[] tipos = Tipo.values();

        this.cartas = new Carta[palos.length * tipos.length];

        int contador = 0;

        for (Palo palo : palos) {
            for (Tipo tipo : tipos) {
                this.cartas[contador++] = new Carta(palo, tipo);
            }
        }
    }

    public Carta[] getCartas() {
        return this.cartas.clone();
    }

    public Mazo crearMazo() {
        return new Mazo(Arrays.asList(this.cartas));
    }

    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        System.out.println(Arrays.toString(baraja.cartas));
    }
}
