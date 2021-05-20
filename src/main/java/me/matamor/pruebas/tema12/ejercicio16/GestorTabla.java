package me.matamor.pruebas.tema12.ejercicio16;

import me.matamor.pruebas.lib.FileUtils;

import java.io.File;

public class GestorTabla {

    private static final File GAME_SAVE = new File(FileUtils.CARPETA_PRUEBAS, "game_save");

    private final GameStorage gameStorage;
    private Tabla tabla;

    public GestorTabla() {
        this.gameStorage = new GameStorage(GAME_SAVE);

        if (this.gameStorage.existe()) {
            try {
                this.tabla = this.gameStorage.load();
            } catch (GameStorage.GameStorageException e) {
                e.printStackTrace();
            }
        }

        if (this.tabla == null) {
            this.tabla = new Tabla();
        }
    }

    public GameStorage getGameStorage() {
        return this.gameStorage;
    }

    public Tabla getTabla() {
        return this.tabla;
    }

    public void guardar() {
        this.gameStorage.save(this.tabla);
    }
}
