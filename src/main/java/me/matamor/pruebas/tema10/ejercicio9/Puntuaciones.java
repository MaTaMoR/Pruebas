package me.matamor.pruebas.tema10.ejercicio9;

import me.matamor.pruebas.lib.Validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puntuaciones {

    private final Map<String, Integer> puntuaciones;

    public Puntuaciones() {
        this.puntuaciones = new HashMap<>();
    }

    public void registrarPuntuacion(String nombre, int puntuacion) {
        Validate.notNull(nombre, "Nombre no puede ser null!");

        this.puntuaciones.put(nombre, puntuacion);
    }

    public boolean tienePuntuacion(String nombre) {
        return this.puntuaciones.containsKey(nombre);
    }

    public int buscarPuntuacion(String nombre) {
        return this.puntuaciones.get(nombre);
    }

    public Map<String, Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public List<Map.Entry<String, Integer>> mejoresPuntuaciones(int cantidad) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(this.puntuaciones.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        if (cantidad >= entries.size()) {
            cantidad = entries.size();
        }

        return entries.subList(0, cantidad);
    }

    public boolean esTop(int puntuacion, int top) {
        if (this.puntuaciones.isEmpty()) {
            return true;
        } else {
            List<Map.Entry<String, Integer>> entries = mejoresPuntuaciones(top);

            for (Map.Entry<String, Integer> entry : entries) {
                if (puntuacion > entry.getValue()) {
                    return true;
                }
            }

            return false;
        }
    }
}
