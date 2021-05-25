package me.matamor.pruebas.blackjack.backup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Backup {

    private final File output;

    private final List<File> files;
    private final List<File> directories;

    public Backup(File output, List<File> files, List<File> directories) throws BackupException {
        if (!output.getName().endsWith(".zip")) {
            throw new BackupException("Output file isn't a Zip!");
        }

        this.output = output;

        this.files = files;
        this.directories = directories;
    }

    public File getOutput() {
        return this.output;
    }

    public List<File> getFiles() {
        return this.files;
    }

    public List<File> getDirectories() {
        return this.directories;
    }

    private void write(String parent, File file, ZipOutputStream outputStream) throws IOException {
        parent = (parent != null ? parent + File.separator + file.getName() : file.getName());

        if (file.isFile()) {
            outputStream.putNextEntry(new ZipEntry(parent));

            byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            outputStream.write(bytes, 0, bytes.length);
            outputStream.closeEntry();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File otherFile : files) {
                    write(parent, otherFile, outputStream);
                }
            }
        }
    }

    public void create() throws BackupException {
        if (this.output.exists() && !this.output.delete()) {
            throw new BackupException("Couldn't delete old backup!");
        }

        if (this.files.size() > 0 || this.directories.size() > 0) {
            try {
                try (FileOutputStream fileOutputStream = new FileOutputStream(this.output)) {
                    try (ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
                        for (File file : this.files) {
                            if (file.exists()) {
                                write(null, file, zipOutputStream);
                            }
                        }

                        for (File file : this.directories) {
                            if (file.exists()) {
                                write(null, file, zipOutputStream);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BackupException extends RuntimeException {

        public BackupException(String message) {
            super(message);
        }
    }
}
