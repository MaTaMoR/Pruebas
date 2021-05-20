package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;

public class Ejercicio14 {

    public static void main(String[] args) {
        File file = new File(FileUtils.CARPETA_PRUEBAS, "adios.txt");
        File file2 = new File(FileUtils.CARPETA_PRUEBAS, "adios2.txt");

        System.out.println(FileUtils.equalsFiles(file, file2));
    }
}
