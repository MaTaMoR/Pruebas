package me.matamor.pruebas.tema10.ejercicio11;

public class Identificadores {

    private int contadorAlumnos;
    private int contadorAsignaturas;
    private int contadorAulas;
    private int contadorGrupos;

    public Identificadores() {
        this.contadorAlumnos = 0;
        this.contadorAsignaturas = 0;
        this.contadorAulas = 0;
        this.contadorGrupos = 0;
    }

    public int nuevoIdentificadorAlumno() {
        return this.contadorAlumnos++;
    }

    public int nuevoIdentificadorAsignaturas() {
        return this.contadorAsignaturas++;
    }

    public int nuevoIdentificadorAulas() {
        return this.contadorAulas++;
    }

    public int nuevoIdentificadorGrupos() {
        return this.contadorGrupos++;
    }
}
