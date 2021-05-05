package me.matamor.pruebas.tema10.ejercicio6;

import me.matamor.pruebas.lib.Validate;

import java.util.HashMap;
import java.util.Map;

public class Traductor {

    private final Map<String, String> traducciones;

    public Traductor() {
        this.traducciones = new HashMap<>();
    }

    public void guardarTraduccion(String palabra, String traduccion) {
        Validate.notNull(palabra, "Palabra no puede ser null!");
        Validate.notNull(traduccion, "Palabra no puede ser null!");

        this.traducciones.put(palabra, traduccion);
    }

    public String traducir(String palabra) {
        Validate.notNull(palabra, "Palabra no puede ser null!");

        return this.traducciones.get(palabra);
    }
}
