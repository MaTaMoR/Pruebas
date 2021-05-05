package me.matamor.pruebas.tema11.ejercicio2;

import me.matamor.pruebas.tema11.ejercicio1.Punto;

import java.util.ArrayList;
import java.util.List;

public class Poligono {

    private final List<Punto> puntos;

    public Poligono(List<Punto> puntos) {
        this.puntos = new ArrayList<>(puntos);
    }

    public void traslada(int x, int y) {
        for (Punto punto : this.puntos) {
            punto.setX(punto.getX() + x);
            punto.setY(punto.getY() + y);
        }
    }

    public int numVertex() {
        return this.puntos.size();
    }

    public double perimetro() {
        if (this.puntos.size() < 3) {
            throw new IllegalStateException("El Poligono necesita al menos 2 puntos para crear un perimetro");
        }

        double perimetro = 0;

        Punto ultimo = this.puntos.get(this.puntos.size() - 1);

        for (Punto punto : this.puntos) {
            perimetro += ultimo.distancia(punto);
            ultimo = punto;
        }

        return perimetro;
    }

    @Override
    public String toString() {
        return "Poligono{" +
                "puntos=" + puntos +
                '}';
    }
}
