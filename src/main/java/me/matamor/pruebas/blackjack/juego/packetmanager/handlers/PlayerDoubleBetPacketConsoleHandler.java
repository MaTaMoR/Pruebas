package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerDoubleBetPacket;

public class PlayerDoubleBetPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerDoubleBetPacket) {
            PlayerDoubleBetPacket doubleBetPacket = (PlayerDoubleBetPacket) packet;

            System.out.printf("El jugador '%s' ha doblado su apuesta '%d'!\n", doubleBetPacket.getJugador().getNombre(), doubleBetPacket.getApuesta());
        }
    }
}
