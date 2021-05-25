package me.matamor.pruebas.blackjack.juego;

import me.matamor.pruebas.blackjack.juego.serializer.MesaSerializer;
import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.lib.config.IConfig;
import me.matamor.pruebas.lib.config.defaults.JsonConfig;
import me.matamor.pruebas.lib.serializer.SerializationException;

import java.io.File;

public class GameSave {

    private final Juego juego;

    private final IConfig config;
    private final MesaSerializer mesaSerializer;

    public GameSave(Juego juego) {
        this.juego = juego;

        this.config = new JsonConfig(new File(FileUtils.CARPETA_PRUEBAS, "game_save.json"));
        this.config.load();

        this.mesaSerializer = new MesaSerializer(juego);
    }

    public boolean guardar(Mesa mesa) {
        try {
            this.config.set("Partida", this.mesaSerializer.serialize(mesa));
            this.config.save();
            return true;
        } catch (SerializationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existe() {
        System.out.println(this.config.exists());
        System.out.println(this.config.contains("Partida"));
        return this.config.exists() && this.config.contains("Partida");
    }

    public Mesa cargar() {
        if (existe()) {
            try {
                return this.mesaSerializer.deserialize(this.config.get("Partida"));
            } catch (SerializationException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
