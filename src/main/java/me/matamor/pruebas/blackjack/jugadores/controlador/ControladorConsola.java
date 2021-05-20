package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.JugadorPersona;
import me.matamor.pruebas.lib.Input;

public class ControladorConsola implements Controlador<JugadorPersona> {

    @Override
    public boolean doblarApuesta(JugadorPersona jugador, Mesa mesa) {
        System.out.println("¿ Quieres doblar tu apuesta ?");
        return Input.leerPregunta();
    }

    @Override
    public Controlador.Respuesta jugar(JugadorPersona jugador, Mesa mesa) {
        System.out.println("¿ Quieres pedir otra carta ?");
        boolean pedirCarta = Input.leerPregunta();

        return (pedirCarta ? Controlador.Respuesta.PEDIR_CARTA : Controlador.Respuesta.SALTAR);
    }
}
