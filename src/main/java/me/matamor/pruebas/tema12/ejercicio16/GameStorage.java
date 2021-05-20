package me.matamor.pruebas.tema12.ejercicio16;

import java.io.*;

public class GameStorage {

    private final File saveFile;

    public GameStorage(File saveFile) {
        this.saveFile = saveFile;
    }

    public boolean existe() {
        return this.saveFile.isFile();
    }

    public boolean borrar() {
        return this.saveFile.delete();
    }

    public void save(Tabla tabla) throws GameStorageException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this.saveFile))) {
            outputStream.writeObject(tabla);
        } catch (IOException e) {
            throw new GameStorageException("No se ha podido guardar la partida!", e);
        }
    }

    public Tabla load() throws GameStorageException {
        if (this.saveFile.isFile()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(this.saveFile))) {
                return (Tabla) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new GameStorageException("No se ha podido cargar la partida guardad!", e);
            }
        } else {
            throw new GameStorageException("No existe una partida guardada!");
        }
    }

    public static class GameStorageException extends RuntimeException {

        public GameStorageException(String message) {
            super(message);
        }

        public GameStorageException(String message, Exception exception) {
            super(message, exception);
        }
    }
}
