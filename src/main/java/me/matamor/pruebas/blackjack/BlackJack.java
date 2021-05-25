package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.juego.packetmanager.ConsolePacketManager;
import me.matamor.pruebas.blackjack.juego.Juego;
import me.matamor.pruebas.blackjack.juego.serializer.MesaSerializer;
import me.matamor.pruebas.blackjack.menu.MenuJuego;

public class BlackJack {

    public void ejecutar() {
        ConsolePacketManager packetManager = new ConsolePacketManager();

        Juego juego = new Juego(packetManager);
        MenuJuego menuJuego = new MenuJuego(juego);

        menuJuego.ejecutar();
    }

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.ejecutar();
    }
}
