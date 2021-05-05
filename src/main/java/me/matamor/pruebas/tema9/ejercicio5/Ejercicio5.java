package me.matamor.pruebas.tema9.ejercicio5;

import me.matamor.pruebas.lib.Input;

public class Ejercicio5 {

    private static final int NOMBRE_MIN = 3;
    private static final int NOMBRE_MAX = -1;

    private static final int APELLIDOS_MIN = 3;
    private static final int APELLIDOS_MAX = -1;

    private static final int EDAD_MIN = 1;
    private static final int EDAD_MAX = 99;

    private static final double ALTURA_MIN = 1;
    private static final double ALTURA_MAX = 300;

    private final Escuela escuela;

    public Ejercicio5() {
        this.escuela = new Escuela();

//        Alumno alumno1 = new Alumno("Santiago", "Gonzalez Londoño", 22, 170);
//        Alumno alumno2 = new Alumno("Pepe", "Giner Izquierdo", 20, 180);
//
//        this.escuela.registrarAlumnos(alumno1, alumno2);
    }

    private void registrarAlumno() {
        System.out.println("Introduce el nombre del alumno:");
        String nombre = Input.leerLinea(NOMBRE_MIN, NOMBRE_MAX);

        System.out.println("Introduce los apellidos del alumno:");
        String apellidos = Input.leerLinea(APELLIDOS_MIN, APELLIDOS_MAX);

        System.out.println("Introduce la edad del alumno:");
        int edad = Input.leerInt(EDAD_MIN, EDAD_MAX);

        System.out.println("Introduce la altura del alumno:");
        double altura = Input.leerDouble(ALTURA_MIN, ALTURA_MAX);

        Alumno alumno = new Alumno(nombre, apellidos, edad, altura);
        this.escuela.registrarAlumno(alumno);

        System.out.println("Se ha registrado correctamente el alumno: \n" + alumno);
    }

    private void mostrarAlumnos() {
        Alumno[] alumnos = this.escuela.alumnos();

        if (alumnos.length == 0) {
            System.out.println("No hay ningun alumno registrado!");
        } else {
            System.out.println("Alumnos:");
            Alumno mayorAlumno = null;

            for (Alumno alumno : alumnos) {
                System.out.println(alumno);

                if (mayorAlumno == null || alumno.getEdad() > mayorAlumno.getEdad()) {
                    mayorAlumno = alumno;
                }
            }

            System.out.println("El alumno mayor es: \n" + mayorAlumno);
        }
    }

    public void ejecutar() {
        final int CANTIDAD = 2;

        for (int i = 0; CANTIDAD > i; i++) {
            registrarAlumno();
        }

        mostrarAlumnos();
    }

    public static void main(String[] args) {
        Ejercicio5 ejercicio5 = new Ejercicio5();
        ejercicio5.ejecutar();
    }
}
