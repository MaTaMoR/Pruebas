package me.matamor.pruebas.blackjack.juego.packetmanager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    public <T extends Packet> void registerHandler(Class<T> clazz) throws PacketException {
        if (this.packetHandlers.containsKey(clazz)) {
            throw new PacketException("There is a handler already registered for this Packet!");
        }

        if (!clazz.isAnnotationPresent(PacketHandlerAnnotation.class)) {
            throw new PacketException("The PacketHandlerAnnotation is missing!");
        }

        PacketHandlerAnnotation annotation = clazz.getAnnotation(PacketHandlerAnnotation.class);
        Class<? extends PacketHandler> packetHandlerClass = annotation.value();

        try {
            Constructor<? extends PacketHandler> constructor = packetHandlerClass.getConstructor();
            PacketHandler packetHandler = constructor.newInstance();

            this.packetHandlers.put(clazz, packetHandler);
        } catch (NoSuchMethodException e) {
            throw new PacketException("The PacketHandler '" + packetHandlerClass.getSimpleName() + "' doesn't have a default Constructor!", e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new PacketException("Couldn't create an instance of PacketHandler '" + packetHandlerClass.getSimpleName() + "'!", e);
        }
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
