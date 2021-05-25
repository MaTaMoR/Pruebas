package me.matamor.pruebas.lib;

import me.matamor.pruebas.lib.config.ISection;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ConfigUtils {

    private ConfigUtils() {

    }

    public static Map<String, Object> asMap(Object object) {
        if (object instanceof ISection) {
            return ((ISection) object).getValues(false);
        } else if (object instanceof Map) {
            Map<String, Object> map = new LinkedHashMap<>();

            ((Map<?, ?>) object).entrySet().stream()
                    .filter(e -> e.getKey() instanceof String)
                    .forEach(e -> map.put((String) e.getKey(), e.getValue()));

            return map;
        }

        return null;
    }
}
