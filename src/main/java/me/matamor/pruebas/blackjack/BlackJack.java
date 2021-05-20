package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.juego.packetmanager.ConsolePacketManager;
import me.matamor.pruebas.blackjack.jugadores.JugadorBot;
import me.matamor.pruebas.blackjack.jugadores.JugadorCPU;
import me.matamor.pruebas.blackjack.jugadores.JugadorPersona;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorBot;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorCPU;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorConsola;
import me.matamor.pruebas.blackjack.jugadores.controlador.RegistroControladores;
import me.matamor.pruebas.blackjack.menu.Juego;
import me.matamor.pruebas.blackjack.menu.MenuJuego;

public class BlackJack {

    public void ejecutar() {
        ConsolePacketManager packetManager = new ConsolePacketManager();

        RegistroControladores registroControladores = new RegistroControladores();
        registroControladores.registrarControlador(JugadorBot.class, new ControladorBot());
        registroControladores.registrarControlador(JugadorCPU.class, new ControladorCPU());
        registroControladores.registrarControlador(JugadorPersona.class, new ControladorConsola());

        Juego juego = new Juego(packetManager, registroControladores);
        MenuJuego menuJuego = new MenuJuego(juego);

        menuJuego.ejecutar();
    }

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.ejecutar();
    }
}
