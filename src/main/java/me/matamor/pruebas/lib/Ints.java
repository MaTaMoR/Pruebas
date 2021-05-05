package me.matamor.pruebas.lib;

public class Ints {

    public static int[] generarInts(int desde, int hasta) {
        if (desde > hasta) {
            throw new IllegalArgumentException("Desde no puede ser mayor que hasta!");
        }

        int diferencia = hasta - desde;
        int[] numeros = new int[diferencia + 1];

        for (int i = 0; numeros.length > i; i++) {
            numeros[i] = desde + i;
        }

        return numeros;
    }
}
