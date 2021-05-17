package me.matamor.pruebas.tema12.ejercicio5;

import java.io.*;

public class GestionArchivos {

    public static boolean crearArchivo(String directorio, String archivo) {
        File file = new File(directorio, archivo);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public static void listarDirectorio(String directorio) {
        File folder = new File(directorio);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("El archivo no existe o no es un directorio!");
        } else {
            File[] files = folder.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("No hay ningún archivo en esta carpeta!");
            } else {
                for (File file : files) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(file.getName()).append(" ");

                    if (file.isDirectory()) {
                        builder.append("d ");
                    } else if (file.isFile()) {
                        builder.append("f ");
                    } else {
                        builder.append("x ");
                    }

                    builder.append(file.length()).append(" bytes");

                    if (file.canRead()) {
                        builder.append(" f");
                    }

                    if (file.canWrite()) {
                        builder.append(" w");
                    }

                    System.out.println(builder.toString());

//                    System.out.printf("%s %s %d bytes %s%s\n",
//                            file.getName(),
//                            file.isDirectory() ? "d" : "f",
//                            file.length(),
//                            file.canRead() ? "r" : "",
//                            file.canWrite() ? "w" : "");
                }
            }
        }
    }

    public static void verInfo(String directorio, String archivo) {
        File file = new File(directorio, archivo);
        if (!file.exists()) {
            System.out.println("El archivo no existe!");
        } else {
            System.out.println("Nombre: " + file.getName());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
            System.out.println("Writeable: " + file.canWrite());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Bytes: " + file.length());
            System.out.println("Directorio: " + file.isDirectory());
            System.out.println("File: " + file.isFile());
        }
    }

    public static void leerArchivo(String directorio, String archivo) {
        File file = new File(directorio, archivo);
        if (!file.exists() || !file.isFile()) {
            System.out.println("El archivo no existe o no es un archivo valido!");
        } else {
            try (FileReader fileReader = new FileReader(file)) {
                int contenido;

                while ((contenido = fileReader.read()) != -1) {
                    System.out.print((char) contenido);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void leerArchivoBinario(String directorio, String archivo) {
        File file = new File(directorio, archivo);
        if (!file.isFile()) {
            System.out.println("No es un archivo valido!");
        } else {
            try (FileInputStream  fileInput = new FileInputStream(file)) {
                int contador = 0;

                while (fileInput.available() > 1) {
                    int value = fileInput.read();
                    System.out.printf("%02X ", value);

                    contador++;

                    if (contador == 10) {
                        System.out.println();
                        contador = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
