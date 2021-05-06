package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerLoseTurnPacket;

public class PlayerLoseTurnConsolePacketHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerLoseTurnPacket) {
            PlayerLoseTurnPacket loseTurnPacket = (PlayerLoseTurnPacket) packet;

            System.out.printf("El jugador '%s' ha perdido el turno al tener '%d' puntos!\n", loseTurnPacket.getJugador().getNombre(), loseTurnPacket.getPuntos());
        }
    }
}
