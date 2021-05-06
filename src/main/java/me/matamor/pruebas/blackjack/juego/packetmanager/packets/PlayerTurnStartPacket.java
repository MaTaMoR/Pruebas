package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerTurnStartPacket implements Packet {

    private Jugador jugador;

    public PlayerTurnStartPacket(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return this.jugador;
    }
}
