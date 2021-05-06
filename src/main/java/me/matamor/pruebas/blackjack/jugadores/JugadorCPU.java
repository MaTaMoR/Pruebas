package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.Mesa;

import java.util.List;

public class JugadorCPU extends Jugador {

    public JugadorCPU() {
        super("CPU");

        setSaldo(Constantes.SALDO_CPU_DEFAULT);
    }

    @Override
    public boolean doblarApuesta(Mesa mesa) {
        int puntuacion = this.mazo.contarPuntosReal();
        int sinCartas = 0;
        int mejorPuntuacion = 0;

        List<Jugador> jugadores = mesa.getJugadores(Estado.ACTIVO);

        for (Jugador jugador : jugadores) {
            if (jugador.getMazo().getCartas().size() > 0) {
                int puntuacionJugador = jugador.getMazo().contarPuntosReal();

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
    public Respuesta jugar(Mesa mesa) {
        int puntosCPU = getMazo().contarPuntosReal();

        return (puntosCPU <= Constantes.PEDIR_CARTA_CPU ? Respuesta.PEDIR_CARTA : Respuesta.SALTAR);
    }
}
