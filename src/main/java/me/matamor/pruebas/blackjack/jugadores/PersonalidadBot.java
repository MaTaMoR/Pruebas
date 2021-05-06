package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.lib.Randomizer;

public enum PersonalidadBot {

    PASIVO(75),
    NORMAL(60),
    AGRESIVO(45);

    private final double riesgo;

    PersonalidadBot(double riesgo) {
        this.riesgo = riesgo;
    }

    public double getRiesgo() {
        return this.riesgo;
    }

    public static PersonalidadBot aleatorio() {
        PersonalidadBot[] personalidades = values();
        return personalidades[Randomizer.randomInt(0, personalidades.length - 1)];
    }
}
