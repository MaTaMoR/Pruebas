package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio10 {

    public static void main(String[] args) {
        File first = new File(FileUtils.CARPETA_PRUEBAS, "hola.txt");
        File second = new File(FileUtils.CARPETA_PRUEBAS, "adios.txt");
        File output = new File(FileUtils.CARPETA_PRUEBAS, "holaadios.txt");

        concatLines(output, first, second);

        FileUtils.printFile(first);
        FileUtils.printFile(second);
        FileUtils.printFile(output);
    }

    public static void concant(File first, File second, File output) {
        FileUtils.concatFiles(output, first, second);
    }

    public static void concatLines(File output, File... files) {
        Map<Integer, StringBuilder> lineas = new HashMap<>();

        for (File file : files) {
            if (file.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    int contador = 0;
                    String line;

                    while ((line = reader.readLine()) != null) {
                        StringBuilder stringBuilder = lineas.get(contador);
                        if (stringBuilder == null) {
                            stringBuilder = new StringBuilder();
                            lineas.put(contador, stringBuilder);

                            stringBuilder.append(line);
                        } else {
                            stringBuilder.append(" ").append(line);
                        }

                        contador++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (lineas.size() > 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                for (StringBuilder stringBuilder : lineas.values()) {
                    writer.write(stringBuilder.toString() + FileUtils.LINE_SEPARATOR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
