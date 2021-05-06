package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerLoseTurnPacket implements Packet {

    private Jugador jugador;
    private int puntos;

    public PlayerLoseTurnPacket(Jugador jugador, int puntos) {
        this.jugador = jugador;
        this.puntos = puntos;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getPuntos() {
        return this.puntos;
    }
}
