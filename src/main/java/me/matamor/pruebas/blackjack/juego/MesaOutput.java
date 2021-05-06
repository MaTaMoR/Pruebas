package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.jugadores.Jugador;

public interface MesaOutput {

    void broadcast(String message);

    void sendMessage(Jugador jugador, String message);

}
