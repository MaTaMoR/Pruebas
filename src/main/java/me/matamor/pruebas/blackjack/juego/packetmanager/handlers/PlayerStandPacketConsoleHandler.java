package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerStandPacket;

public class PlayerStandPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerStandPacket) {
            PlayerStandPacket standPacket = (PlayerStandPacket) packet;

            System.out.printf("El jugador '%s' se planta con '%d' puntos!\n", standPacket.getJugador().getNombre(), standPacket.getPuntos());
        }
    }
}
