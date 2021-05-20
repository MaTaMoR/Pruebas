package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.Jugador;

public interface Controlador<T extends Jugador> {

    boolean doblarApuesta(T jugador, Mesa mesa);

    Respuesta jugar(T jugador, Mesa mesa);

    enum Respuesta {

        SALTAR,
        PEDIR_CARTA

    }

}
