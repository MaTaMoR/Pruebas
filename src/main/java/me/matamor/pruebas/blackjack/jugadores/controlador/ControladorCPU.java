package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorCPU;

import java.util.List;

public class ControladorCPU implements Controlador<JugadorCPU> {

    @Override
    public boolean doblarApuesta(JugadorCPU jugador, Mesa mesa) {
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
        return Constantes.CPU_MIN_SIN >= porcentajeCartas && Constantes.CPU_MIN_MEJOR >= porcentajePuntuacion;
    }

    @Override
    public Controlador.Respuesta jugar(JugadorCPU jugador, Mesa mesa) {
        int puntosCPU = jugador.getMazo().contarPuntosReal();

        return (puntosCPU <= Constantes.PEDIR_CARTA_CPU ? Controlador.Respuesta.PEDIR_CARTA : Controlador.Respuesta.SALTAR);
    }
}
