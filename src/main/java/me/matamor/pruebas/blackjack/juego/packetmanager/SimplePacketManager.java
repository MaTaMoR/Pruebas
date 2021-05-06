package me.matamor.pruebas.blackjack.juego.packetmanager;

import java.util.HashMap;
import java.util.Map;

public abstract class SimplePacketManager implements PacketManager {

    protected final Map<Class<?>, PacketHandler> packetHandlers;

    public SimplePacketManager() {
        this.packetHandlers = new HashMap<>();
    }

    @Override
    public <T extends Packet> void registerHandler(Class<T> clazz, PacketHandler handler) throws PacketException {
        if (this.packetHandlers.containsKey(clazz)) {
            throw new PacketException("There is a handler already registered for this Packet!");
        }

        this.packetHandlers.put(clazz, handler);
    }

    @Override
    public void handlePacket(Packet packet) throws PacketException {
        PacketHandler packetHandler = this.packetHandlers.get(packet.getClass());
        if (packetHandler == null) {
            throw new PacketException("There isn't a registered handler for this Packet!");
        }

        packetHandler.handle(packet);
    }
}
