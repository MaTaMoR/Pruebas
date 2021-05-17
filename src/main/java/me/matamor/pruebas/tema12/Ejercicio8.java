package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.tema12.ejercicio5.GestionArchivos;

public class Ejercicio8 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorrecto: <directorio> <archivo>");
            System.exit(0);
        }

        String directorio = args[0];
        if (directorio.equalsIgnoreCase("home")) {
            directorio = FileUtils.CARPETA_PRUEBAS.getAbsolutePath();
        }

        GestionArchivos.leerArchivo(directorio, args[1]);
    }
}
