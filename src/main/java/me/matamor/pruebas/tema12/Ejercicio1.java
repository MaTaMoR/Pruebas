package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

public class Ejercicio1 {

    public static void main(String[] args) {
        System.out.println("Existe: " + FileUtils.CARPETA_HOME.exists());
        System.out.println("Es carpeta: " + FileUtils.CARPETA_HOME.isDirectory());
    }
}
