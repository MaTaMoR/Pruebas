package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerTiePacket implements Packet {

    private Jugador jugador;
    private int puntuacion;

    public PlayerTiePacket(Jugador jugador, int puntuacion) {
        this.jugador = jugador;
        this.puntuacion = puntuacion;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }
}
