package me.matamor.pruebas.blackjack.juego.packetmanager.handlers;

import me.matamor.pruebas.blackjack.juego.packetmanager.Packet;
import me.matamor.pruebas.blackjack.juego.packetmanager.PacketHandler;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.GameStatePacket;

public class GameStatePacketConsoleHandler implements PacketHandler {

    @Override
    public void handle(Packet packet) {
        if (packet instanceof GameStatePacket) {
            GameStatePacket statePacket = (GameStatePacket) packet;

            System.out.println("--------------------------------");

            switch (statePacket.getEstadoMesa()) {
                case ACTIVA: {
                    System.out.println("COMIENZA LA PARTIDA");
                    break;
                }
                case DECIDE: {
                    System.out.println("CALCULANDO GANADORES");
                    break;
                }
                case PAY: {
                    System.out.println("PAGANDO APUESTAS");
                    break;
                }
                case FINISHING: {
                    System.out.println("PREPARANDO MESA PARA SIGUIENTE MANO");
                    break;
                }
                case PARADA: {
                    System.out.println("ACABA LA PARTIDA");
                    break;
                }
            }

            System.out.println("--------------------------------");
        }
    }
}
