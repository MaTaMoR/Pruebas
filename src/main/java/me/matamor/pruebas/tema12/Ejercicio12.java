package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio12 {

    private static final char[] LETRAS = new char[] { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
    private static final int VALID_LENGTH = 8;
    private static final String OUTPUT_NAME = "_conLetras";

    public static void main(String[] args) {
        convertir(new File(FileUtils.CARPETA_PRUEBAS, "dnis.txt"));
    }

    private static void convertir(File file) throws ConversionException {
        if (file.isFile()) {
            List<String> validados = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.length() > VALID_LENGTH) {
                        throw new ConversionException("Dato invalido: " + line);
                    } else if (line.length() < VALID_LENGTH) {
                        line = "0".repeat(VALID_LENGTH - line.length()) + line;
                    }

                    validados.add(line + calcularLetra(line));
                }

                if (validados.size() > 0) {
                    String nombre = file.getName();
                    int indexOf = nombre.lastIndexOf('.');
                    if (indexOf != -1) {
                        String rawNombre = nombre.substring(0, indexOf);
                        String extension = nombre.substring(indexOf);

                        nombre = rawNombre + OUTPUT_NAME + extension;
                    } else {
                        nombre = nombre + OUTPUT_NAME;
                    }

                    File output = new File(file.getParent(), nombre);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                        for (String linea : validados) {
                            writer.write(linea + FileUtils.LINE_SEPARATOR);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static char calcularLetra(String dni) throws ConversionException {
        try {
            int numero = Integer.parseInt(dni);
            int resto = numero % 23;

            return LETRAS[resto];
        } catch (NumberFormatException e) {
            throw new ConversionException("Invalid number: " + dni);
        }
    }
    public static class ConversionException extends RuntimeException {

        public ConversionException(String message) {
            super(message);
        }
    }
}
