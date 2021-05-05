package me.matamor.pruebas.tema10.ejercicio8;

import me.matamor.pruebas.lib.Validate;

import java.util.HashMap;
import java.util.Map;

public class Diccionario {

    private final Map<String, String> definiciones;

    public Diccionario() {
        this.definiciones = new HashMap<>();
    }

    public void registrarDefinicion(String palabra, String definicion) {
        Validate.notNull(palabra, "Palabra no puede ser null!");
        Validate.notNull(definicion, "Definicio no puede ser null!");

        this.definiciones.put(palabra, definicion);
    }

    public boolean existeDefinicion(String palabra) {
        return this.definiciones.containsKey(palabra);
    }

    public String buscarDefinicion(String palabra) {
        return this.definiciones.get(palabra);
    }

    public Map<String, String> definiciones() {
        return this.definiciones;
    }
}
