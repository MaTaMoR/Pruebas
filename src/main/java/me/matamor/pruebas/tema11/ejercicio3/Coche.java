package me.matamor.pruebas.tema11.ejercicio3;

import java.util.Arrays;

public class Coche {

    private String matricula;
    private int[] marchas;
    private int marcha;
    private int velocidad;

    public Coche(String matricula, int[] marchas) {
        this.matricula = matricula;
        this.marchas = marchas;

        this.velocidad = 0;
        this.marcha = 0;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public int[] getMarchas() {
        return this.marchas;
    }

    public int getMarcha() {
        return this.marcha;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    protected void setVelocidad(int velocidad) throws CocheException {
        if (velocidad < 0) {
            throw new CocheException("El coche no puede tener una velocidad negativa");
        }

        this.velocidad = velocidad;
    }

    protected int velocidadMaximaMarchaActual() {
        return this.marchas[this.marcha];
    }

    protected int velocidadMinimaMarchaActual() {
        return (this.marcha == 0 ? 0 : this.marchas[this.marcha - 1]);
    }

    protected int velocidadMaxima() {
        return this.marchas[this.marchas.length - 1];
    }

    public void acelerar(int cantidad) throws CocheException {
        int nuevaVelocidad = this.velocidad + cantidad;
        int velocidadMaxima = velocidadMaximaMarchaActual();

        if (nuevaVelocidad > velocidadMaxima) {
            nuevaVelocidad = velocidadMaxima;
        }

        this.velocidad = nuevaVelocidad;
    }

    public void frenar(int cantidad) throws CocheException {
        int nuevaVelocidad = this.velocidad - cantidad;
        int velocidadMinima = velocidadMinimaMarchaActual();

        if (nuevaVelocidad < velocidadMinima) {
            nuevaVelocidad = velocidadMinima;
        }

        this.velocidad = nuevaVelocidad;
    }

    protected void cambiarMarcha(int marcha) throws CocheException {
        if (marcha < 0 || marcha >= this.marchas.length) {
            throw new CocheException("La nueva marcha está fuera del rango de marchas!");
        }

        this.marcha = marcha;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", marchas=" + Arrays.toString(marchas) +
                ", marcha=" + marcha +
                ", velocidad=" + velocidad +
                '}';
    }
}
