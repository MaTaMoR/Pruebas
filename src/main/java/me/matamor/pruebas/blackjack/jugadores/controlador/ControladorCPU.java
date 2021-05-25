package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.Jugador;

import java.util.List;

public class ControladorCPU implements Controlador {

    @Override
    public boolean doblarApuesta(Mazo mazo, Mesa mesa) {
        int puntuacion = mazo.contarPuntosReal();
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
        return Constantes.CPU_MIN_SIN >= porcentajeCartas && Constantes.CPU_MIN_MEJOR >= porcentajePuntuacion;
    }

    @Override
    public Controlador.Respuesta jugar(Mazo mazo, Mesa mesa) {
        int puntosCPU = mazo.contarPuntosReal();

        return (puntosCPU <= Constantes.PEDIR_CARTA_CPU ? Controlador.Respuesta.PEDIR_CARTA : Controlador.Respuesta.SALTAR);
    }
}
