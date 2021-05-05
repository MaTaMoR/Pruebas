package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorBot;
import me.matamor.pruebas.blackjack.jugadores.JugadorPersona;
import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Input;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private Mesa mesa;

    private Juego() {

    }

    private Jugador pedirJugador() {
        System.out.println("Introduce tu nombre:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        return new JugadorPersona(nombre);
    }

    private List<Jugador> pedirJugadores() {
        List<Jugador> jugadores = new ArrayList<>();

        boolean activo = false;

        do {
            try {
                System.out.println("¿ Quieres que el jugador sea un bot ? ");
                boolean bot = Input.leerPregunta();

                String nombre;
                if (bot) {
                    nombre = "Bot - " + jugadores.size();
                } else {
                    System.out.println("Introduce el nombre del jugador:");
                    nombre = Input.leerLinea(Constantes.NAME_MIN, -1);
                }

                Jugador jugador = (bot ? new JugadorBot(nombre) : new JugadorPersona(nombre));
                jugadores.add(jugador);

                System.out.println("Nuevo jugador registrado: " + jugador.getNombre());

                System.out.println("¿ Quieres añadir otro jugador a la partida ?");
                activo = Input.leerPregunta();
            } catch (CancelException e) {
                System.out.println("Se ha cancelado añadir un nuevo jugador!");
            }
        } while (activo);

        return jugadores;
    }

    public void crearPartida() {
        if (this.mesa != null) {
            System.out.println("Ya hay una partida activa!");
            return;
        }

        /* Aquí se guarda el listado de jugadores */
        List<Jugador> jugadores = new ArrayList<>();

        /* Creamos el jugador principal y lo añadimos al listado de jugadores */
        Jugador jugador = pedirJugador();
        jugadores.add(jugador);

        /* Preguntamos si el jugador quiere añadir más jugadores */
        System.out.println("¿ Quieres añadir un jugador a la partida ?");
        boolean masJugadores = Input.leerPregunta();

        /* Añadimos todos los jugadores que el jugador añada */
        if (masJugadores) {
            jugadores.addAll(pedirJugadores());
        }

        Mesa mesa = new Mesa(jugadores);
        this.mesa = mesa;
    }
}
