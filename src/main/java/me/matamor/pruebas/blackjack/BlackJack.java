package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.juego.packetmanager.ConsolePacketManager;
import me.matamor.pruebas.blackjack.menu.Juego;
import me.matamor.pruebas.blackjack.menu.MenuJuego;

public class BlackJack {

    public void ejecutar() {
        Juego juego = new Juego(new ConsolePacketManager());
        MenuJuego menuJuego = new MenuJuego(juego);

        menuJuego.ejecutar();
    }

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.ejecutar();
    }
}
