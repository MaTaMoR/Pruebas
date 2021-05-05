package me.matamor.pruebas.tema10.ejercicio11;

import java.util.Objects;

public class Aula {

    private final int identificador;
    private final double metrosCuadrados;

    public Aula(int identificador, double metrosCuadrados) {
        this.identificador = identificador;
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public double getMetrosCuadrados() {
        return this.metrosCuadrados;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.identificador);
    }


    @Override
    public String toString() {
        return String.format("Id: %d, Metros cuadrados: %.2f", this.identificador, this.metrosCuadrados);
    }
}
