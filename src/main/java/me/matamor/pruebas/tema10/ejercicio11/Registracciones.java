package me.matamor.pruebas.tema10.ejercicio11;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

import java.util.ArrayList;
import java.util.List;

public class Registracciones extends MenuConsola {

    private final Escuela escuela;

    public Registracciones(Escuela escuela) {
        super("Registracciones");

        this.escuela = escuela;

        registrarOpcion(Constantes.REGISTRAR_AULA, new OpcionSimple("Registrar Aula", "Registra un aula nueva", this::registrarAula));
        registrarOpcion(Constantes.REGISTRAR_PROFESOR, new OpcionSimple("Registrar Profesor", "Registra un nuevo profesor", this::registrarProfesor));
        registrarOpcion(Constantes.REGISTRAR_ASIGNATURA, new OpcionSimple("Registrar Asignatura", "Registra una nueva asignatura", this::registrarAsignatura));
        registrarOpcion(Constantes.REGISTRAR_GRUPO, new OpcionSimple("Registrar Grupo", "Registra un nuevo grupo", this::registrarGrupo));
        registrarOpcion(Constantes.REGISTRAR_ALUMNO, new OpcionSimple("Registrar Alumno", "Registra un alumno nuevo", this::registrarAlumno));
    }

    private void registrarAula() {
        System.out.println("Introduce los metros cuadrados del aula:");
        double metrosCuadrados = Input.leerDouble(Constantes.METROS_CUADRADOS_MIN, -1);

        Aula aula = this.escuela.registrarAula(metrosCuadrados);
        System.out.println("Aula registrada correctamente! Identificador: " + aula.getIdentificador());
    }

    private void registrarProfesor() {
        System.out.println("Introduce el DNI del profesor:");
        String dni = Input.leerLinea(Constantes.DOCUMENTO_LENGTH, Constantes.DOCUMENTO_LENGTH);

        System.out.println("Introduce el nombre del profesor:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        System.out.println("Introduce el salario del profesor:");
        double salario = Input.leerDouble(Constantes.SALARIO_MIN, Constantes.SALARIO_MAX);

        try {
            Profesor profesor = this.escuela.registrarProfesor(dni, nombre, salario);
            System.out.println("Profesor registrado correctamente! Identificador: " + profesor.getDni());
        } catch (EscuelaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void registrarAsignatura() {
        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        System.out.println("Introduce el identificador del profesor que dara la asignatura:");
        String dni = Input.leerLinea(Constantes.DOCUMENTO_LENGTH, Constantes.DOCUMENTO_LENGTH);

        try {
            Asignatura asignatura = this.escuela.registrarAsignatura(nombre, dni);
            System.out.println("Asignatura registrada correctamente! Identificador: " + asignatura.getIdentificador());
        } catch (EscuelaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void registrarGrupo() {
        System.out.println("Introduce el nombre del grupo:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        System.out.println("Introduce el identificador del aula del grupo:");
        int identificadorAula = Input.leerInt(Constantes.IDENTIFIER_MIN, -1);

        try {
            Grupo grupo = this.escuela.registrarGrupo(nombre, identificadorAula);
            System.out.println("Grupo registrado correctamente! Identificador: " + grupo.getIdentificador());
        } catch (EscuelaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void registrarAlumno() {
        System.out.println("Introduce el nombre del alumno:");
        String nombre = Input.leerLinea(Constantes.NAME_MIN, -1);

        System.out.println("Introduce el identificador del grupo del alumno:");
        int identificadorGrupo = Input.leerInt(Constantes.IDENTIFIER_MIN, -1);

        System.out.println("Introduce los identificadores de cada asignatura separado por comas:");

        String input = Input.leerLinea();
        String[] partes = input.split(",");

        List<Integer> identificadoresAsignaturas = new ArrayList<>();
        for (String parte : partes) {
            try {
                identificadoresAsignaturas.add(Integer.parseInt(parte));
            } catch (NumberFormatException ignored) {

            }
        }

        try {
            Alumno alumno = this.escuela.registrarAlumno(nombre, identificadorGrupo, identificadoresAsignaturas);
            System.out.println("Alumno registrado correctamente! Identificador: " + alumno.getIdentificador());
        } catch (EscuelaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
