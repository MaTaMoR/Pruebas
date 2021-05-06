package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerTiePacket;

public class PlayerTiePacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerTiePacket) {
            PlayerTiePacket tiePacket = (PlayerTiePacket) packet;

            System.out.printf("El jugador '%s' ha empatado con la CPU con '%d' puntos!\n", tiePacket.getJugador().getNombre(), tiePacket.getPuntuacion());
        }
    }
}
