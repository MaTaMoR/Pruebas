package me.matamor.pruebas.tema12.ejercicio16;

import java.io.Serializable;
import java.util.Arrays;

public class Tabla implements Serializable {

    private static final int TRES_EN_RAYA = 3;

    private final Ficha[][] tabla;
    private final int[] puntuaciones;

    private Ficha siguienteTurno;

    public Tabla() {
        this.tabla = new Ficha[TRES_EN_RAYA][TRES_EN_RAYA];
        this.puntuaciones = new int[Ficha.values().length];
        this.siguienteTurno = null;
    }

    public void jugar(int x, int y, Ficha ficha) {
        this.tabla[x][y] = ficha;

        Ficha[] fichas = Ficha.values();
        int posicion = ficha.ordinal();

        if (posicion + 1 >= fichas.length) {
            posicion = 0;
        } else {
            posicion = posicion + 1;
        }

        this.siguienteTurno = fichas[posicion];
    }

    public Ficha ficha(int x, int y) {
        return this.tabla[x][y];
    }

    public Ficha getSiguienteTurno() {
        return this.siguienteTurno;
    }

    public void aumentarPuntuacion(Ficha ficha) {
        int posicion = ficha.ordinal();
        int puntuacionActual = this.puntuaciones[posicion];

        this.puntuaciones[posicion] = puntuacionActual + 1;
    }

    public void print() {
        System.out.println(Arrays.deepToString(this.tabla));
    }
}
