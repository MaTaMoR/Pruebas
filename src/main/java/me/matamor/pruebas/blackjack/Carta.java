package me.matamor.pruebas.blackjack;

import java.util.Objects;

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
