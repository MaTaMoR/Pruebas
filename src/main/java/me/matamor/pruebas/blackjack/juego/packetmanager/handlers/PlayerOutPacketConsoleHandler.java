package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerOutPacket;

public class PlayerOutPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerOutPacket) {
            PlayerOutPacket outPacket = (PlayerOutPacket) packet;

            System.out.printf("El jugador '%s' se ha quedado sin suficiente dinero para la apuesta, por lo tanto se retira!\n", outPacket.getJugador().getNombre());
        }
    }
}
