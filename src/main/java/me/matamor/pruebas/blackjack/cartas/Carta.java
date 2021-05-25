package me.matamor.pruebas.blackjack.cartas;

import java.util.Objects;

public class Carta {

    private final Palo palo;
    private final Tipo tipo;

    private boolean visisble;

    public Carta(Palo palo, Tipo tipo) {
        this(palo, tipo, true);
    }

    public Carta(Palo palo, Tipo tipo, boolean visisble) {
        this.palo = palo;
        this.tipo = tipo;
        this.visisble = visisble;
    }

    public Palo getPalo() {
        return this.palo;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public boolean isVisisble() {
        return this.visisble;
    }

    public void setVisible(boolean visisble) {
        this.visisble = visisble;
    }

    @Override
    public String toString() {
        return this.palo.getNombre() + ":" + this.tipo.getNombre();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Carta carta = (Carta) obj;
        return this.palo == carta.palo && this.tipo == carta.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.palo, this.tipo);
    }
}
