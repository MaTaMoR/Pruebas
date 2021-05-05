package me.matamor.pruebas.tema11.ejercicio7.estadio;

public class Equipo {

    private final String nombre;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
