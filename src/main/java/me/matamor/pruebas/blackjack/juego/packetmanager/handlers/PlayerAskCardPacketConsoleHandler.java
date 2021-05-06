package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerAskCardPacket;

public class PlayerAskCardPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerAskCardPacket) {
            PlayerAskCardPacket askCardPacket = (PlayerAskCardPacket) packet;

            System.out.printf("El jugador '%s' pide una carta más!\n", askCardPacket.getJugador().getNombre());
        }
    }
}
