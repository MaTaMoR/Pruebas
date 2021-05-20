package me.matamor.pruebas.blackjack.jugadores.controlador;

import me.matamor.pruebas.blackjack.jugadores.Jugador;

import java.util.HashMap;
import java.util.Map;

public class RegistroControladores {

    private final Map<Class<? extends Jugador>, Controlador<?>> controladores;

    public RegistroControladores() {
        this.controladores = new HashMap<>();
    }

    public <T extends Jugador> void registrarControlador(Class<T> jugador, Controlador<T> controlador) {
        if (this.controladores.containsKey(jugador)) {
            throw new IllegalArgumentException("Ya hay un controlador registrado para ese jugador!");
        }

        this.controladores.put(jugador, controlador);
    }

    public <T extends Jugador> Controlador<T> getControlador(Class<T> jugador) {
        Controlador<?> controlador = this.controladores.get(jugador);
        return controlador == null ? null : (Controlador<T>) controlador;
    }
}
