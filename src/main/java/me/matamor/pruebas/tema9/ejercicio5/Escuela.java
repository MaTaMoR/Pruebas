package me.matamor.pruebas.tema9.ejercicio5;

import me.matamor.pruebas.lib.Validate;

public class Escuela {

    private static final int BASE_SIZE = 10;
    private static final int INCREMENT_SIZE = 10;

    private Alumno[] alumnos;
    private int contador;

    public Escuela() {
        this.alumnos = new Alumno[BASE_SIZE];
        this.contador = 0;
    }

    private void asegurarCapacidad() {
        if (this.contador >= this.alumnos.length) {
            Alumno[] alumnos = new Alumno[this.alumnos.length + INCREMENT_SIZE];
            System.arraycopy(this.alumnos, 0, alumnos, 0, this.alumnos.length);

            this.alumnos = alumnos;
        }
    }

    public void registrarAlumno(Alumno alumno) {
        Validate.notNull(alumno, "El alumno no puede ser null!");

        asegurarCapacidad();
        this.alumnos[this.contador++] = alumno;
    }

    public void registrarAlumnos(Alumno... alumnos) {
        Validate.notNull(alumnos, "Los alumnos no pueden ser null!");

        for (Alumno alumno : alumnos) {
            registrarAlumno(alumno);
        }
    }

    public Alumno[] alumnos() {
        Alumno[] alumnos = new Alumno[this.contador];
        System.arraycopy(this.alumnos, 0, alumnos, 0, this.contador);

        return alumnos;
    }
}
