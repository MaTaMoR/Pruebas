package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.jugadores.Jugador;

public class MesaOutputConsola implements MesaOutput {

    @Override
    public void broadcast(String message) {
        System.out.println(message);
    }

    @Override
    public void sendMessage(Jugador jugador, String message) {
        System.out.println(message);
    }
}
