package me.matamor.pruebas.tema11.ejercicio7.entradas;

import me.matamor.pruebas.tema11.ejercicio7.estadio.RegistroEntradas;

public class EntradaNormal extends Entrada {

    private final int numeroSorteo;

    public EntradaNormal(RegistroEntradas registroEntradas, int zona, int fila, int asiento, double precio, int numeroSorteo) {
        super(registroEntradas, zona, fila, asiento, precio);

        this.numeroSorteo = numeroSorteo;
    }

    public int getNumeroSorteo() {
        return this.numeroSorteo;
    }
}
