package me.matamor.pruebas.tema11.ejercicio6.multimedia;

import me.matamor.pruebas.lib.Keyable;

import java.util.ArrayList;
import java.util.List;

public class Plataforma implements Keyable<String> {

    private final String nombre;
    private final List<Videojuego> videojuegos;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.videojuegos = new ArrayList<>();
    }

    @Override
    public String getKey() {
        return this.nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void nuevoVideojuego(Videojuego videojuego) {
        if (!this.videojuegos.contains(videojuego)) {
            this.videojuegos.add(videojuego);
        }
    }

    public void borrarVideojuego(Videojuego videojuego) {
        this.videojuegos.remove(videojuego);
    }

    public List<Videojuego> videojuegos() {
        return this.videojuegos;
    }

    @Override
    public String toString() {
        return "Plataforma{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
