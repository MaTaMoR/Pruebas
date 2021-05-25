package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.Mesa;

public interface Controlador {

    boolean doblarApuesta(Mazo mazo, Mesa mesa);

    Respuesta jugar(Mazo mazo, Mesa mesa);

    enum Respuesta {

        SALTAR,
        PEDIR_CARTA

    }

}
