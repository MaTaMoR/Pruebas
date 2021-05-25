package me.matamor.pruebas.lib.config.defaults;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.matamor.pruebas.lib.ConfigUtils;
import me.matamor.pruebas.lib.config.IConfig;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class JsonConfig extends MemoryISection implements IConfig {

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final File file;

    public JsonConfig(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public boolean exists() {
        return this.file.exists();
    }

    private void put(String parent, Map<String, Object> content) {
        for (Map.Entry<String, Object> entry : content.entrySet()) {
            String path = (parent == null ? entry.getKey() : parent + "." + entry.getKey());

            if (entry.getValue() instanceof Map) {
                Map<String, Object> map = ConfigUtils.asMap(entry.getValue());

                if (map != null) {
                    put(path, map);
                }
            } else {
                set(path, entry.getValue());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void load() {
        this.content.clear();

        if (this.file.exists()) {
            try {
                Map<String, Object> content = gson.fromJson(new FileReader(this.file), HashMap.class);
                put(null, content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save() {
        Map<String, Object> content = getValues(true);

        this.file.delete();

        String json = gson.toJson(content); // Remember pretty printing? This is needed here.

        try {
            Files.write(this.file.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
