package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerTurnStartPacket;

public class PlayerTurnStartPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerTurnStartPacket) {
            PlayerTurnStartPacket turnStartPacket = (PlayerTurnStartPacket) packet;

            System.out.println("--------------------------------");
            System.out.printf("Dando cartas al jugador '%s'!\n", turnStartPacket.getJugador().getNombre());
            System.out.println("--------------------------------");
        }
    }
}
