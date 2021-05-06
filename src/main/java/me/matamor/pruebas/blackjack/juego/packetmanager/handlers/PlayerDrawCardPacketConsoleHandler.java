package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.cartas.CardPrinter;
import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.PlayerDrawCardPacket;

public class PlayerDrawCardPacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof PlayerDrawCardPacket) {
            PlayerDrawCardPacket drawCardPacket = (PlayerDrawCardPacket) packet;

            if (drawCardPacket.getCarta().isVisisble()) {
                CardPrinter.print(drawCardPacket.getCarta());
            } else {
                System.out.printf("El jugador '%s' ha sacado una carta oculta!\n", drawCardPacket.getJugador().getNombre());
            }
        }
    }
}
