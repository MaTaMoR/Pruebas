package me.matamor.pruebas.blackjack.juego.packetmanager;

import me.matamor.pruebas.blackjack.jugadores.Jugador;

public interface PacketManager {

    <T extends Packet> void registerHandler(Class<T> clazz, PacketHandler handler) throws PacketException;

    void handlePacket(Packet packet) throws PacketException;

    void broadcast(Packet packet) throws PacketException;

    void sendMessage(Jugador jugador, Packet packet) throws PacketException;

}
