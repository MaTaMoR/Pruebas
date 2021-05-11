package me.matamor.pruebas.blackjack.juego.packetmanager;

public class PacketException extends RuntimeException {

    public PacketException(String message) {
        super(message);
    }

    public PacketException(String message, Exception exception) {
        super(message, exception);
    }
}
