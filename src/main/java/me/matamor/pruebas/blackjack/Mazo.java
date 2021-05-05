package me.matamor.pruebas.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private final List<Carta> cartas;

    public Mazo() {
        this(new ArrayList<>());
    }

    public Mazo(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public List<Carta> getCartas() {
        return this.cartas;
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public Carta sacarCarta() {
        return this.cartas.remove(0);
    }
}
