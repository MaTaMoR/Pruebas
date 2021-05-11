package me.matamor.pruebas.lib;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static final String USER_HOME = System.getProperty("user.home");
    public static final File CARPETA_HOME = new File(USER_HOME + File.separator + "programacion");

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

    public static boolean deleteDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
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

    public static void copy(File source, File target) throws IOException {
        List<String> ignore = Arrays.asList("uid.dat", "session.dat", "chests.yml");

        if (ignore.contains(source.getName())) {
            return;
        }

        if (source.isDirectory()) {
            if (!target.exists() && !target.exists()) {
                throw new IOException("Couldn't create target file!");
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
            try (InputStream in = new FileInputStream(source)) {
                try (OutputStream out = new FileOutputStream(target)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                }
            }
        }
    }
}
