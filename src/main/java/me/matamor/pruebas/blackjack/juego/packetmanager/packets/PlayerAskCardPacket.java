package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerAskCardPacket implements Packet {

    private Jugador jugador;

    public PlayerAskCardPacket(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return this.jugador;
    }
}
