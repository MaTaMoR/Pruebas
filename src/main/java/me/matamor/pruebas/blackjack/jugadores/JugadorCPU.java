package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.juego.Mesa;

public class JugadorCPU extends Jugador {

    public JugadorCPU() {
        super("CPU");
    }

    @Override
    public Respuesta jugar(Mesa mesa) {
        int puntosCPU = getMazo().contarPuntosReal();

        return (puntosCPU <= Constantes.PEDIR_CARTA_CPU ? Respuesta.PEDIR_CARTA : Respuesta.SALTAR);
    }
}
