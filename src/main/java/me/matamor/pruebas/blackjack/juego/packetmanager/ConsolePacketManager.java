package me.matamor.pruebas.blackjack.juego.packetmanager;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.juego.packetmanager.handlers.*;
import me.matamor.pruebas.blackjack.juego.packetmanager.packets.*;

import java.util.Collection;

public class ConsolePacketManager extends SimplePacketManager {

    public ConsolePacketManager() {
        registerHandler(PlayerTurnStartPacket.class, new PlayerTurnStartPacketConsoleHandler());
        registerHandler(PlayerDrawCardPacket.class, new PlayerDrawCardPacketConsoleHandler());
        registerHandler(PlayerStandPacket.class, new PlayerStandPacketConsoleHandler());
        registerHandler(PlayerWinTurnPacket.class, new PlayerWinTurnConsolePacketHandler());
        registerHandler(PlayerLoseTurnPacket.class, new PlayerLoseTurnConsolePacketHandler());
        registerHandler(PlayerTiePacket.class, new PlayerTiePacketConsoleHandler());
        registerHandler(PlayerOutPacket.class, new PlayerOutPacketConsoleHandler());
        registerHandler(PlayerBetPacket.class, new PlayerBetPacketConsoleHandler());
        registerHandler(PlayerAskCardPacket.class, new PlayerAskCardPacketConsoleHandler());
        registerHandler(PlayerPointsPacket.class, new PlayerPointsPacketConsolaHandler());
        registerHandler(PlayerDoubleBetPacket.class, new PlayerDoubleBetPacketConsoleHandler());
        registerHandler(GameStatePacket.class, new GameStatePacketConsoleHandler());
    }

    @Override
    public void broadcast(Collection<Jugador> jugadores, Packet packet) throws PacketException {
        handlePacket(packet);
    }

    @Override
    public void sendMessage(Jugador jugador, Packet packet) throws PacketException {
        handlePacket(packet);
    }
}
