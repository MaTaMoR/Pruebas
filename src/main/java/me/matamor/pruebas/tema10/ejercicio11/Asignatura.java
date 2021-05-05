package me.matamor.pruebas.tema10.ejercicio11;

import java.util.Objects;

public class Asignatura {

    private final int identificador;
    private final String nombre;

    private Profesor profesor;

    public Asignatura(int identificador, String nombre, Profesor profesor) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.identificador);
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Nombre: %s, Profesor: %s", this.identificador, this.nombre, this.profesor);
    }
}
