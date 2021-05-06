package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerBetPacket;

public class PlayerBetPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerBetPacket) {
            PlayerBetPacket betPacket = (PlayerBetPacket) packet;

            if (betPacket.isGana()) {
                System.out.printf("El jugador '%s' gana '%d' (%.2f) por su apuesta!\n", betPacket.getJugador().getNombre(), betPacket.getApuesta(), betPacket.getBonificador());
            } else {
                System.out.printf("El jugador '%s' pierde '%d' por su apuesta!\n", betPacket.getJugador().getNombre(), betPacket.getApuesta());
            }
        }
    }
}
