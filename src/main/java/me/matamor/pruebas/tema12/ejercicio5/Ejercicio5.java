package me.matamor.pruebas.tema12.ejercicio5;

import me.matamor.pruebas.lib.Input;

public class Ejercicio5 {

    public static void main(String[] args) {
        crearArchivo();
        listarDirectorio();
        verInfo();
    }

    private static void crearArchivo() {
        System.out.println("Introduce el directorio del archivo:");
        String directorio = Input.leerLinea(1, -1);

        System.out.println("Introduce el nombre del archivo:");
        String archivo = Input.leerLinea(1, -1);

        if (GestionArchivos.crearArchivo(directorio, archivo)) {
            System.out.println("Archivo creado correctamente!");
        } else {
            System.out.println("No se ha podido crear el archivo!");
        }
    }

    private static void listarDirectorio() {
        System.out.println("Introduce el directorio del archivo:");
        String directorio = Input.leerLinea(1, -1);

        GestionArchivos.listarDirectorio(directorio);
    }

    private static void verInfo() {
        System.out.println("Introduce el directorio del archivo:");
        String directorio = Input.leerLinea(1, -1);

        System.out.println("Introduce el nombre del archivo:");
        String archivo = Input.leerLinea(1, -1);

        GestionArchivos.verInfo(directorio, archivo);
    }
}
