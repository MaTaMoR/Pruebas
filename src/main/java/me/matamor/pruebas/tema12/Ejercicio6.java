package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.tema12.ejercicio5.GestionArchivos;

public class Ejercicio6 {

    public static void main(String[] args) {
        leerArchivo();
    }

    private static void leerArchivo() {
        System.out.println("Introduce el directorio del archivo:");
        String directorio = Input.leerLinea(1, -1);
        if (directorio.equalsIgnoreCase("home")) {
            directorio = FileUtils.CARPETA_PRUEBAS.getAbsolutePath();
        }

        System.out.println("Introduce el nombre del archivo:");
        String archivo = Input.leerLinea(1, -1);

        GestionArchivos.leerArchivo(directorio, archivo);
    }
}
