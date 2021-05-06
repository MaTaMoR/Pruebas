package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerPointsPacket implements Packet {

    private Jugador jugador;
    private int points;

    public PlayerPointsPacket(Jugador jugador, int points) {
        this.jugador = jugador;
        this.points = points;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getPoints() {
        return this.points;
    }
}
