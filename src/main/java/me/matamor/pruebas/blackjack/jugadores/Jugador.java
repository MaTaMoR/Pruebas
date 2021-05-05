package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.Juego;
import me.matamor.pruebas.blackjack.Mazo;

public abstract class Jugador {

    private final String nombre;
    private final Mazo mazo;

    private int saldo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new Mazo();

        this.saldo = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Mazo getMazo() {
        return this.mazo;
    }

    public abstract void jugar(Juego juego);
}
