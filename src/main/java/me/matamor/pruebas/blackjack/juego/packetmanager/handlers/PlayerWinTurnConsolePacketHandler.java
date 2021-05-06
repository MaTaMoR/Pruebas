package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerWinTurnPacket;

public class PlayerWinTurnConsolePacketHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerWinTurnPacket) {
            PlayerWinTurnPacket winTurnPacket = (PlayerWinTurnPacket) packet;

            System.out.printf("El jugador '%s' ha conseguido '%d' puntos y gana esta mano!\n", winTurnPacket.getJugador().getNombre(), winTurnPacket.getPuntos());
        }
    }
}
