package me.matamor.pruebas.lib;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static final String USER_HOME = System.getProperty("user.home");
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static final File CARPETA_HOME = new File(USER_HOME);
    public static final File CARPETA_PRUEBAS = new File(CARPETA_HOME, "programacion");

    public static File getAvailableFolder(File fileContainer, String fileName) {
        String actualName = fileName;
        File file;
        int count = 1;

        while ((file = new File(fileContainer, actualName)).exists()) {
            actualName = fileName + " " + count++;
        }

        return file;
    }

    public static File getAvailableFile(File fileContainer, String fileName, String extension) {
        String actualName = fileName;
        File file;
        int count = 1;

        while ((file = new File(fileContainer, actualName + extension)).exists()) {
            actualName = fileName + "_" + count++;
        }

        return file;
    }

    /**
     * Borra un archivo, si es una carpeta borra su contenido
     * @param directory la carpeta a borrar
     * @return true si se borro correctamente
     */

    public static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null){
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (!deleteDirectory(file)) {
                            return false;
                        }
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
        }

        return directory.delete();
    }

    /**
     * Copia un archivo a otro, si es una carpeta copia el contenido
     * @param source la carpeta origen
     * @param target la carpeta destino

     * @throws FileException si da un error al leer el archivo
     */

    public static void copy(File source, File target) throws FileException {
        if (source.isDirectory()) {
            if (!target.exists() && !target.exists()) {
                throw new FileException("Couldn't create target file!");
            }

            String[] files = source.list();

            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(target, file);

                    copy(srcFile, destFile);
                }
            }
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(source))) {
                try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
                    out.write(in.readLine());
                }
            } catch (IOException e) {
                throw new FileException("No se ha podido leer el archivo o escribir el output!");
            }
        }
    }

    /**
     * Muestra el contenido del file, si es una carpeta muestra todos los archivos recursivamente
     * @param file el archivo a mostrar
     * @throws FileException si da un error al leer el archivo
     */

    public static void printFile(File file) throws FileException {
        if (file.isFile()) {
            System.out.println(file.getPath());

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new FileException("No se ha podido leer el archivo!", e);
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                for (File child : files) {
                    printFile(child);
                }
            }
        }
    }

    /**
     * Copia el contenido de todos los archivos al mismo archivo
     * @param output donde se guardara el contenido de todos los archivos
     * @param files todos los archivos a copiar
     * @throws FileException si da un error al leer el archivo
     */

    public static void concatFiles(File output, File... files) throws FileException {
        if (files.length > 0) {
            try (BufferedWriter writer
                         = new BufferedWriter(new FileWriter(output))) {
                for (File file : files) {
                    if (file.isFile()) {
                        try (BufferedReader reader
                                     = new BufferedReader(new FileReader(file))) {
                            String line;

                            while ((line = reader.readLine()) != null) {
                                writer.write(line + FileUtils.LINE_SEPARATOR);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new FileException("No se ha podido leer los archivos o escribir el output!", e);
            }
        }
    }

    /**
     * Comprueba que ambos archivos tengan el mismo contenido
     * @param a el primer archivo
     * @param b el segundo archivo
     * @return true si ambos archivos contienen el mismo contenido
     * @throws FileException si da un error al leer el archivo
     */

    public static boolean equalsFiles(File a, File b) throws FileException {
        if (!a.isFile() || !b.isFile()) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        try (FileReader aInput = new FileReader(a);
             FileReader bInput = new FileReader(b)) {

            int input;
            while ((input = aInput.read()) != -1) {
                if (input != bInput.read()) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            throw new FileException("No se ha podido leer los archivos!", e);
        }
    }

    /**
     * Comprueba que ambos archivos tengan el mismo hash
     * @param a el primer archivo
     * @param b el segundo archivo
     * @return true si ambos archivos contienen el mismo contenido
     * @throws FileException si da un error al leer el archivo
     */

    public static boolean equalsFilesHash(File a, File b) throws FileException {
        if (!a.isFile() || !b.isFile()) {
            return false;
        }

        if (a.length() != b.length()) {
            return false;
        }

        byte[] hashA = getHash(a);
        byte[] hashB = getHash(b);

        return Arrays.equals(hashA, hashB);
    }

    public static byte[] getHash(File file) throws FileException {
        if (file.isFile()) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                try (InputStream inputStream = new FileInputStream(file)) {
                    int value;

                    while ((value = inputStream.read()) != -1) {
                        messageDigest.update((byte) value);
                    }
                    
                    return messageDigest.digest();
                } catch (IOException e) {
                    throw new FileException("No se ha podido leer el archivo!");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new FileException("No se puede cargar MD5!");
            }
        } else {
            throw new FileException("No es un archivo valido!");
        }
    }

    public static class FileException extends RuntimeException {

        public FileException(String message) {
            super(message);
        }

        public FileException(String message, Exception exception) {
            super(message, exception);
        }
    }
}
