package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.cartas.Baraja;
import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.cartas.Tipo;
import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorBot;

import java.util.List;
import java.util.stream.Collectors;

public class ControladorBot implements Controlador<JugadorBot> {

    @Override
    public boolean doblarApuesta(JugadorBot jugador, Mesa mesa) {
        int puntuacion = jugador.getMazo().contarPuntosReal();
        int sinCartas = 0;
        int mejorPuntuacion = 0;

        List<Jugador> jugadores = mesa.getJugadores(Jugador.Estado.ACTIVO);

        for (Jugador otroJugador : jugadores) {
            if (otroJugador.getMazo().getCartas().size() > 0) {
                int puntuacionJugador = otroJugador.getMazo().contarPuntosReal();

                if (puntuacion > puntuacionJugador) {
                    mejorPuntuacion = mejorPuntuacion + 1;
                }
            } else {
                sinCartas = sinCartas + 1;
            }
        }

        double porcentajePuntuacion = (double) (puntuacion * 100) / jugadores.size();
        double porcentajeCartas = (double) (sinCartas) * 100 / jugadores.size();

        //Si se cumple el minimo de cartas así como el minimo de jugadores
        return jugador.getPersonalidadBot().getMinSin() >= porcentajeCartas &&
                jugador.getPersonalidadBot().getMinMejor() >= porcentajePuntuacion;
    }

    /**
     * Coge todas las cartas actuales de la mesa
     * @param mesa la mesa que se está jugando
     * @return todas las cartas de la mesa
     */

    private List<Carta> cartasEnMesa(Mesa mesa) {
        List<Carta> cartas = mesa.getJugadores().stream()
                .map(e -> e.getMazo().getCartasVisibles())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        cartas.addAll(mesa.getCpu().getMazo().getCartasVisibles());
        return cartas;
    }

    @Override
    public Controlador.Respuesta jugar(JugadorBot jugadorBot, Mesa mesa) {
        // Contamos nuestros puntos
        int puntosJugador = jugadorBot.getMazo().contarPuntos();

        //Sacamos todas las cartas que son visibles en la mesa
        List<Carta> cartas = cartasEnMesa(mesa);

        //Cogemos todas las que hay en la Baraja
        Mazo mazo = Baraja.nuevoMazo();
        cartas.forEach(mazo::quitarCarta);        //Quitamos las cartas que ya hay en la mesa

        //Los puntos restantes que nos faltan para ganar
        int puntosRestantes = Constantes.PUNTOS_GANAR - puntosJugador;

        //Contamos todas las cartas que quedan por salir que nos sirven
        int cartasValidas = (int) mazo.getCartas().stream().filter(e -> {
            if (e.getTipo() == Tipo.AS) {
                return Constantes.CARTA_AS_MIN <= puntosRestantes;
            } else {
                return e.getTipo().getValor() <= puntosRestantes;
            }
        }).count();

        //Calculamos la probabilidad que nos salga una carta que nos sirve
        double porcentajeCartaValida = (double) (cartasValidas * 100) / mazo.getCartas().size();

        System.out.printf("a= %.2f b= %d c= %d\n", porcentajeCartaValida, cartasValidas, puntosRestantes);

        //Comprobamos si nuestro bot se arriesga con la probabilidad dada
        boolean pedirCarta = porcentajeCartaValida >= jugadorBot.getPersonalidadBot().getRiesgo();

        //Devolvemos la acción que haría nuestro bot
        return (pedirCarta ? Controlador.Respuesta.PEDIR_CARTA : Controlador.Respuesta.SALTAR);
    }
}
