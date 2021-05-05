package me.matamor.pruebas.blackjack;

public class Carta {

    private final Palo palo;
    private final Tipo tipo;

    public Carta(Palo palo, Tipo tipo) {
        this.palo = palo;
        this.tipo = tipo;
    }

    public Palo getPalo() {
        return this.palo;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    @Override
    public String toString() {
        return this.palo.getNombre() + ":" + this.tipo.getNombre();
    }
}
