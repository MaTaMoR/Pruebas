package me.matamor.pruebas.blackjack.juego.packetmanager.packets;

import me.matamor.pruebas.blackjack.juego.EstadoMesa;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;

public class GameStatePacket implements Packet {

    private EstadoMesa estadoMesa;

    public GameStatePacket(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public EstadoMesa getEstadoMesa() {
        return this.estadoMesa;
    }
}
