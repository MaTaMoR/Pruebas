package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerPointsPacket;

public class PlayerPointsPacketConsolaHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerPointsPacket) {
            PlayerPointsPacket pointsPacket = (PlayerPointsPacket) packet;

            System.out.printf("El jugador '%s' tiene '%d' puntos!\n", pointsPacket.getJugador().getNombre(), pointsPacket.getPoints());
        }
    }
}
