package me.matamor.pruebas.blackjack.juego.packetmanager;

import me.matamor.pruebas.blackjack.jugadores.Jugador;

import java.util.Collection;

public interface PacketManager {

    <T extends Packet> void registerHandler(Class<T> clazz, PacketHandler handler) throws PacketException;

    <T extends Packet> void registerHandler(Class<T> clazz) throws PacketException;

    void handlePacket(Packet packet) throws PacketException;

    void broadcast(Collection<Jugador> jugadores, Packet packet) throws PacketException;

    void sendMessage(Jugador jugador, Packet packet) throws PacketException;

}
