package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;

public class Ejercicio14 {

    public static void main(String[] args) {
        File file = new File(FileUtils.CARPETA_PRUEBAS, "adios.txt");
        File file2 = new File(FileUtils.CARPETA_PRUEBAS, "adios2.txt");

        System.out.println(equalsFiles(file, file2));
    }

    public static boolean equalsFiles(File a, File b) {
        if (!(a.isFile() && b.isFile())) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        try (BufferedReader aInput = new BufferedReader(new FileReader(a));
                BufferedReader bInput = new BufferedReader(new FileReader(b))) {

            String line;
            while ((line = aInput.readLine()) != null) {
                if (!line.equals(bInput.readLine())) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
