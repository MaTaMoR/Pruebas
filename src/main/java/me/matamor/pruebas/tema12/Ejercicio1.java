package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.File;

public class Ejercicio1 {

    public static void main(String[] args) {
        File carpeta = FileUtils.CARPETA_PRUEBAS;

        System.out.println("Existe: " + carpeta.exists());
        System.out.println("Es carpeta: " + carpeta.isDirectory());
    }
}
