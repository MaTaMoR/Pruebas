package me.matamor.pruebas.tema10.ejercicio11;

import java.util.Objects;

public class Grupo {

    private final int identificador;
    private final String nombre;

    private Aula aula;

    public Grupo(int identificador, String nombre, Aula aula) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.aula = aula;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Aula getAula() {
        return this.aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.identificador);
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Nombre: %s", this.identificador, this.nombre);
    }
}
