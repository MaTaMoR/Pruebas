package me.matamor.pruebas.tema11.ejercicio1;

import me.matamor.pruebas.lib.Validate;

public class Punto {

    private double x, y;

    public Punto() {
        this(0, 0);
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distancia(Punto punto) {
        Validate.notNull(punto, "Punto no puede ser null!");

        double diferenciaX = this.x - punto.getX();
        double diferenciaY = this.y - punto.getY();

        return Math.sqrt((diferenciaX * diferenciaX) + (diferenciaY * diferenciaY));
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", this.x, this.y);
    }
}
