package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class PlayerBetPacket implements Packet {

    private Jugador jugador;
    private int apuesta;
    private double bonificador;
    private boolean gana;

    public PlayerBetPacket(Jugador jugador, int apuesta, double bonificador, boolean gana) {
        this.jugador = jugador;
        this.apuesta = apuesta;
        this.bonificador = bonificador;
        this.gana = gana;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public int getApuesta() {
        return this.apuesta;
    }

    public double getBonificador() {
        return this.bonificador;
    }

    public boolean isGana() {
        return this.gana;
    }
}
