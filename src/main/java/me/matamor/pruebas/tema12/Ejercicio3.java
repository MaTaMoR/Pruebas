package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.File;

public class Ejercicio3 {

    public static void main(String[] args) {
        File carpeta = FileUtils.CARPETA_PRUEBAS;

        if (!carpeta.exists()) {
            System.out.println("La carpeta no existe!");
        } else {
            System.out.println("Nombre: " + carpeta.getName());
            System.out.println("Ruta absoluta: " + carpeta.getAbsolutePath());
            System.out.println("Readable: " + carpeta.canRead());
            System.out.println("Writeable: " + carpeta.canWrite());
        }
    }
}
