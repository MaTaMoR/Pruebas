package me.matamor.pruebas.tema10.ejercicio11;

import java.util.Objects;

public class Profesor {

    private final String dni;
    private final String nombre;
    private double sueldo;

    public Profesor(String dni, String nombre, double sueldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return this.dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dni);
    }

    @Override
    public String toString() {
        return String.format("DNI: %s, Nombre: %s, Sueldo: %.2f", this.dni, this.nombre, this.sueldo);
    }
}
