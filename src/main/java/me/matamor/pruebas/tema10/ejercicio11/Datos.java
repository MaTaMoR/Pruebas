package me.matamor.pruebas.tema10.ejercicio11;

import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.List;

public class Datos extends MenuConsola {

    private final Escuela escuela;

    public Datos(Escuela escuela) {
        super("Datos");

        this.escuela = escuela;

        registrarOpcion(Constantes.AULAS, new OpcionSimple("Aulas", "Listado de todas las aulas", this::aulas));
        registrarOpcion(Constantes.PROFESORES, new OpcionSimple("Profesores", "Listado de todos los profesores", this::profesores));
        registrarOpcion(Constantes.ASIGNATURAS, new OpcionSimple("Asignaturas", "Listado de todas las asignaturas", this::asignaturas));
        registrarOpcion(Constantes.GRUPOS, new OpcionSimple("Grupos", "Listado de todos los grupos", this::grupos));
        registrarOpcion(Constantes.ALUMNOS, new OpcionSimple("Alumnos", "Listado de todos los alumnos", this::alumnos));
    }

    private void aulas() {
        List<Aula> aulas = this.escuela.aulas();
        if (aulas.isEmpty()) {
            System.out.println("No hay ninguna aula registrada!");
        } else {
            System.out.println("Aulas:");
            for (Aula aula : aulas) {
                System.out.println("- " + aula);
            }
        }
    }

    private void profesores() {
        List<Profesor> profesores = this.escuela.profesores();
        if (profesores.isEmpty()) {
            System.out.println("No hay ningún profesor registrado!");
        } else {
            System.out.println("Profesores:");
            for (Profesor profesor : profesores) {
                System.out.println("- " + profesor);
            }
        }
    }

    private void asignaturas() {
        List<Asignatura> asignaturas = this.escuela.asignaturas();
        if (asignaturas.isEmpty()) {
            System.out.println("No hay ninguna asignatura registrada!");
        } else {
            System.out.println("Asignaturas:");
            for (Asignatura asignatura : asignaturas) {
                System.out.println("- " + asignatura);
            }
        }
    }

    private void grupos() {
        List<Grupo> grupos = this.escuela.grupos();
        if (grupos.isEmpty()) {
            System.out.println("No hay ningún grupo registrado!");
        } else {
            System.out.println("Grupos:");
            for (Grupo grupo : grupos) {
                System.out.println("- " + grupo);
            }
        }
    }

    private void alumnos() {
        List<Alumno> alumnos = this.escuela.alumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay ningún alumno registrado!");
        } else {
            System.out.println("Alumnos:");
            for (Alumno alumno : alumnos) {
                System.out.println("- " + alumno);
            }
        }
    }
}
