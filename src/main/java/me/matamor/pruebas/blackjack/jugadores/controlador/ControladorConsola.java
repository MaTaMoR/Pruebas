package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.lib.Input;

public class ControladorConsola implements Controlador {

    @Override
    public boolean doblarApuesta(Mazo mazo, Mesa mesa) {
        System.out.println("¿ Quieres doblar tu apuesta ?");
        return Input.leerPregunta();
    }

    @Override
    public Controlador.Respuesta jugar(Mazo mazo, Mesa mesa) {
        System.out.println("¿ Quieres pedir otra carta ?");
        boolean pedirCarta = Input.leerPregunta();

        return (pedirCarta ? Controlador.Respuesta.PEDIR_CARTA : Controlador.Respuesta.SALTAR);
    }
}
