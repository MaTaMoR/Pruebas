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

                Jugador jugador;

                //Si el jugador es un bot tiene un nombre y apuesta predeterminado
                if (bot) {
                    jugador = new JugadorBot("Bot-" + jugadores.size());
                    jugador.setApuesta(Constantes.APUESTA_DEFAULT);
                } else {
                    //Preguntamos el nombre del jugador
                    System.out.println("Introduce el nombre del jugador:");
                    String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

                    System.out.printf("Introduce la apuesta del jugador (Apuesta minima: %d):\n", Constantes.APUESTA_DEFAULT);
                    int apuesta = Input.leerInt(Constantes.APUESTA_DEFAULT, Constantes.SALDO_DEFAULT);

                    jugador = new JugadorPersonaConsola(nombre);
                    jugador.setApuesta(apuesta);
                }

                //Añadimos el jugador al listado de jugadores
                jugadores.add(jugador);

                System.out.println("Nuevo jugador registrado: " + jugador.getNombre());

                //Preguntamos si quiere añadir un jugador más
                System.out.println("¿ Quieres añadir otro jugador a la partida ?");
                activo = Input.leerPregunta();
            } catch (CancelException e) {
                System.out.println("Se ha cancelado añadir un nuevo jugador!");
            }
        } while (activo && jugadores.size() < Constantes.JUGADORES_MAXIMOS);

        return jugadores;
    }

    private void crearPartida() {
        if (this.juego.isPartidaActiva()) { //Comprobamos que no haya un partida ya activa, aunque en teoría no debería ser posible
            System.out.println("Ya hay una partida activa!");
        } else { //Creamos una partida nueva
            System.out.println("==== CREACIÓN DE PARTIDA ====");

            //Aquí se guarda el listado de jugadores
            List<Jugador> jugadores = new ArrayList<>();

            //Creamos el jugador principal y lo añadimos al listado de jugadores
            Jugador jugador = pedirJugador();
            jugadores.add(jugador);

            //Preguntamos si el jugador quiere añadir más jugadores
            System.out.println("¿ Quieres añadir más jugadores a la partida ?");
            boolean masJugadores = Input.leerPregunta();

            //Añadimos todos los jugadores que el jugador añada
            if (masJugadores) {
                jugadores.addAll(pedirJugadores());
            }

            try {
                //Creamos la mesa donde con los jugadores e iniciamos la partida
                Mesa mesa = this.juego.crearPartida(jugadores);
                mesa.iniciar();
            } catch (BlackJackException e) {
                System.out.println("No se ha podido crear la partida: " + e.getMessage());
            }
        }
    }

    private void mostrarEstadisticas() {
        Mesa mesa = this.juego.getMesa();
        if (mesa == null) { //Comprobamos que haya una partida
            System.out.println("Todavía no se ha jugado ninguna partida!");
        } else if (mesa.getEstadoMesa() != EstadoMesa.PARADA) { //Comprobamos que la partida haya acabado
            System.out.println("La mesa actual todavía no ha acabado!");
        } else { //Mostramos las estadisticas
            System.out.println("==== ESTADISTICAS DE ÚLTIMA PARTIDA ====");
            System.out.println("Total de manos: " + mesa.getManos());
            System.out.println("Jugadores: " + mesa.getJugadores().size());
            System.out.println("Manos ganada por CPU: " + mesa.getCpu().getManosGanadas());

            for (Jugador jugador : mesa.getJugadores()) {
                System.out.printf("Manos ganadas por '%s': %d\n", jugador.getNombre(), jugador.getManosGanadas());
            }
        }
    }
}
