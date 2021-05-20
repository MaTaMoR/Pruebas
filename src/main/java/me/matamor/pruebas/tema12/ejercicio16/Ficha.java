package me.matamor.pruebas.tema12.ejercicio16;

import java.io.Serializable;

public enum Ficha implements Serializable {

    X,
    O;

    @Override
    public String toString() {
        return name();
    }
}
