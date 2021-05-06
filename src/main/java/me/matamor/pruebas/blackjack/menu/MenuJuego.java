package me.matamor.pruebas.blackjack.menu;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.exceptions.BlackJackException;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorBot;
import me.matamor.pruebas.blackjack.jugadores.JugadorPersonaConsola;
import me.matamor.pruebas.blackjack.juego.EstadoMesa;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.lib.CancelException;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.ArrayList;
import java.util.List;

public class MenuJuego extends MenuConsola {

    private final Juego juego;
    
    public MenuJuego(Juego juego) {
        super("Juego");

        this.juego = juego;

        registrarOpcion(Constantes.NUEVA_PARTIDA, new OpcionSimple("Nueva Partida", "Crea una partida nueva!", this::crearPartida));
        registrarOpcion(Constantes.ESTADISTICAS, new OpcionSimple("Mostrar estadisticas", "Muestra las estadisticas de la ultima partida!", this::mostrarEstadisticas));
    }

    private Jugador pedirJugador() {
        System.out.println("Introduce tu nombre:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        return new JugadorPersonaConsola(nombre);
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

                Jugador jugador = (bot ? new JugadorBot(nombre) : new JugadorPersonaConsola(nombre));
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

    private void crearPartida() {
        if (this.juego.isPartidaActiva()) {
            System.out.println("Ya hay una partida activa!");
        } else {
            System.out.println("==== CREACIÓN DE PARTIDA ====");

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

            try {
                Mesa mesa = this.juego.crearPartida(jugadores);
                mesa.iniciar();
            } catch (BlackJackException e) {
                System.out.println("No se ha podido crear la partida: " + e.getMessage());
            }
        }
    }

    private void mostrarEstadisticas() {
        Mesa mesa = this.juego.getMesa();
        if (mesa == null) {
            System.out.println("Todavía no se ha jugado ninguna partida!");
        } else if (mesa.getEstadoMesa() != EstadoMesa.PARADA) {
            System.out.println("La mesa actual todavía no ha acabado!");
        } else {
            System.out.println("==== ESTADISTICAS DE ÚLTIMA PARTIDA ====");
            System.out.println("Total de manos: " + mesa.getManos());
            System.out.println("Jugadores: " + mesa.getJugadores().size());
            System.out.println("Manos ganada por CPU: " + mesa.getCpu().getManosGanadas());

            for (Jugador jugador : mesa.getJugadores()) {
                System.out.printf("Manos ganadas por %s: %d\n", jugador.getNombre(), jugador.getManosGanadas());
            }
        }
    }
}
