package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.jugadores.Jugador;

public class PlayerDoubleBetPacket implements Packet {

    private Jugador jugador;
    private int apuesta;

    public PlayerDoubleBetPacket(Jugador jugador, int apuesta) {
        this.jugador = jugador;
        this.apuesta = apuesta;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getApuesta() {
        return this.apuesta;
    }
}
