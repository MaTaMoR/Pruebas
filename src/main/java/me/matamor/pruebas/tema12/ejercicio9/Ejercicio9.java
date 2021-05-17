package me.matamor.pruebas.tema12.ejercicio9;

import me.matamor.pruebas.lib.FileUtils;

public class Ejercicio9 {

    public static void main(String[] args) {
        GestorAlumnos gestorAlumnos = new GestorAlumnos(FileUtils.CARPETA_PRUEBAS.getAbsolutePath(), "alumnos.bin");

        String[] alumnos = new String[] {
                "Santiago Gonzalez",
                "Daniel Ferrer",
                "Cristobal Bernal"
        };

        gestorAlumnos.guardarAlumnos(alumnos);
        System.out.println(gestorAlumnos.cargarAlumnos());

        gestorAlumnos.borrarAlumno(alumnos[0]);
        System.out.println(gestorAlumnos.cargarAlumnos());
    }
}
