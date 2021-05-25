package me.matamor.pruebas.blackjack.backup;

import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.lib.config.defaults.JsonConfig;
import me.matamor.pruebas.lib.serializer.SerializationException;

import java.io.File;

public class EjercicioBackup {

    public static void main(String[] args) {
        BackupSerializer serializer = new BackupSerializer();
        JsonConfig config = new JsonConfig(new File(FileUtils.CARPETA_PRUEBAS, "backup.json"));
        config.load();

        try {
            Backup backup = serializer.deserialize(config.get("Backup"));
            backup.create();
        } catch (SerializationException e) {
            e.printStackTrace();
        }
    }
}
