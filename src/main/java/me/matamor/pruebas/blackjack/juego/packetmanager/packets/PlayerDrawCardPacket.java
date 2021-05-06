package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.cartas.Carta;
import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerDrawCardPacket implements Packet {

    private Jugador jugador;
    private Carta carta;

    public PlayerDrawCardPacket(Jugador jugador, Carta carta) {
        this.jugador = jugador;
        this.carta = carta;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public Carta getCarta() {
        return this.carta;
    }
}
