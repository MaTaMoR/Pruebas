package me.matamor.pruebas.tema9.ejercicio5;

public class Alumno {

    private final String nombre;
    private final String apellidos;
    private final int edad;
    private final double altura;

    public Alumno(String nombre, String apellidos, int edad, double altura) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " Apellidos: " + this.apellidos + " Edad: " + this.edad + " Altura: " + this.edad;
    }
}
