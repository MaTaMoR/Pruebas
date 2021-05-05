package me.matamor.pruebas.tema10.ejercicio11;

import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.List;

public class Consultas extends MenuConsola {

    private final Escuela escuela;

    public Consultas(Escuela escuela) {
        super("Consultas");

        this.escuela = escuela;

        registrarOpcion(Constantes.POR_GRUPO, new OpcionSimple("Por grupo", "Busca los alumnos ordenador por su grupo", this::porGrupo));
        registrarOpcion(Constantes.POR_PROFESOR, new OpcionSimple("Por profesor", "Busca los alumnos ordenador por su profesor", this::porProfesor));
    }

    private void porGrupo() {
        List<Grupo> grupos = this.escuela.grupos();
        if (grupos.isEmpty()) {
            System.out.println("No hay ningún grupo registrado!");
        } else {
            for (Grupo grupo : grupos) {
                System.out.println("Grupo: " + grupo.getNombre());

                List<Alumno> alumnos = this.escuela.buscarAlumnos(alumno -> alumno.getGrupo().equals(grupo));
                if (alumnos.isEmpty()) {
                    System.out.println("No hay alumnos registrados en este grupo!");
                } else {
                    System.out.println("Alumnos:");
                    for (Alumno alumno : alumnos) {
                        System.out.println("- " + alumno);
                    }
                }
            }
        }
    }

    private void porProfesor() {
        List<Profesor> profesores = this.escuela.profesores();
        if (profesores.isEmpty()) {
            System.out.println("No hay ningún profesor registrado!");
        } else {
            for (Profesor profesor : profesores) {
                System.out.println("Profesor: " + profesor.getNombre());

                List<Alumno> alumnos = this.escuela.buscarAlumnos(alumno ->
                        alumno.getAsignaturas().stream().anyMatch(e -> e.getProfesor().equals(profesor)));

                if (alumnos.isEmpty()) {
                    System.out.println("No hay alumnos registrados con este profesor!");
                } else {
                    System.out.println("Alumnos:");
                    for (Alumno alumno : alumnos) {
                        System.out.println("- " + alumno);
                    }
                }
            }
        }
    }
}
