package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorBot;

public class JugadorBot extends Jugador {

    private final PersonalidadBot personalidadBot;

    public JugadorBot(String nombre) {
        super(nombre, new ControladorBot());

        this.personalidadBot = PersonalidadBot.aleatorio();
    }

    public PersonalidadBot getPersonalidadBot() {
        return this.personalidadBot;
    }

}
