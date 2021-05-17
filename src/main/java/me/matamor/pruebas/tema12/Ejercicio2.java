package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.File;

public class Ejercicio2 {

    public static void main(String[] args) {
        File carpeta = FileUtils.CARPETA_PRUEBAS;

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La carpeta no existe o no es un directorio!");
        } else {
            File[] files = carpeta.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("No hay ningún archivo en la carpeta!");
            } else {
                System.out.println("Archivos de la carpeta: ");

                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
