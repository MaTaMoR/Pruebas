package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.jugadores.JugadorBot;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorPersona;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private final Baraja baraja;
    private final Jugador jugador;

    private final List<Jugador> jugadores;

    private Juego() {
        this.baraja = new Baraja();
        this.jugador = new JugadorPersona("Jugador");

        this.jugadores = new ArrayList<>();
        this.jugadores.add(this.jugador);
    }

    public Baraja getBaraja() {
        return this.baraja;
    }

    public Jugador nuevoJugador(boolean bot, String nombre) {
        Jugador jugador;
        if (bot) {
            jugador = new JugadorBot(nombre);
        } else {
            jugador = new JugadorPersona(nombre);
        }

        this.jugadores.add(jugador);
        return jugador;
    }

    public boolean borrarJugador(String nombre) {
        return this.jugadores.removeIf(e -> e != this.jugador && e.getNombre().equalsIgnoreCase(nombre));
    }

    private void reiniciar() {
        this.jugadores.forEach(e -> e.setSaldo(0));

    }

    private void jugar() {

    }
}
