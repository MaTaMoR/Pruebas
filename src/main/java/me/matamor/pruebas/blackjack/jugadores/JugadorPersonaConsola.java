package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.lib.Input;

public class JugadorPersonaConsola extends JugadorPersona {

    public JugadorPersonaConsola(String nombre) {
        super(nombre);
    }

    @Override
    public Respuesta jugar(Mesa mesa) {
        System.out.println("¿ Quieres pedir otra carta ?");
        boolean pedirCarta = Input.leerPregunta();

        return (pedirCarta ? Respuesta.PEDIR_CARTA : Respuesta.SALTAR);
    }
}
