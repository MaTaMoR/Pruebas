package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.lib.Randomizer;

public enum PersonalidadBot {

    PASIVO(70, 15, 85),
    NORMAL(55, 20, 80),
    AGRESIVO(40, 25, 75);

    private final double riesgo;
    private final double minSin;
    private final double minMejor;

    PersonalidadBot(double riesgo, double minSin, double minMejor) {
        this.riesgo = riesgo;
        this.minSin = minSin;
        this.minMejor = minMejor;
    }

    public double getRiesgo() {
        return this.riesgo;
    }

    public double getMinSin() {
        return this.minSin;
    }

    public double getMinMejor() {
        return this.minMejor;
    }

    public static PersonalidadBot aleatorio() {
        PersonalidadBot[] personalidades = values();
        return personalidades[Randomizer.randomInt(0, personalidades.length - 1)];
    }
}
