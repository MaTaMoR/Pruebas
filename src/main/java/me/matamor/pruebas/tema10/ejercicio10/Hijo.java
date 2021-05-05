package me.matamor.pruebas.tema10.ejercicio10;

public class Hijo {

    private final String nombre;
    private final int edad;

    public Hijo(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s Edad: %d", this.nombre, this.edad);
    }
}
