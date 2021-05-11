package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.lib.MemorySize;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Ejercicio4 {

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    public static void main(String[] args) {
        File carpeta = FileUtils.CARPETA_HOME;

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La carpeta no existe o no es un directorio!");
        } else {
            File[] files = carpeta.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("No hay ningún archivo en la carpeta!");
            } else {
                File archivo = files[0];

                System.out.println("Nombre: " + archivo.getName());
                System.out.println("Ruta abosluta: " + archivo.getAbsolutePath());
                System.out.println("Oculto: " + archivo.isHidden());
                System.out.println("Readable: " + archivo.canRead());
                System.out.println("Writeable: " + archivo.canWrite());
                System.out.println("Last modification: " + dateFormat.format(archivo.lastModified()));
                if (!archivo.setLastModified(System.currentTimeMillis())) {
                    System.out.println("No se pudo modificar la fecha de la ultima modificacion!");
                }
                System.out.println("Last modification: " + dateFormat.format(archivo.lastModified()));
                System.out.println("Bytes: " + archivo.length());
                System.out.println("KB: " + MemorySize.KILO_BYTE.convert(archivo.length(), MemorySize.BYTE));
                System.out.println("MB: " + MemorySize.MEGA_BYTE.convert(archivo.length(), MemorySize.BYTE));
            }
        }
    }
}
