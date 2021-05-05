package me.matamor.pruebas.tema10.ejercicio2;

import me.matamor.pruebas.lib.Randomizer;

import java.util.Arrays;

public class Ejercicio2 {

    public void execute() {
        final int CANTIDAD = 20;
        final int MIN = 0;
        final int MAX = 10;

        System.out.printf("Valores: %d Min: %d Max: %d\n", CANTIDAD, MIN, MAX);

        int[] valores = Randomizer.randomIntArray(CANTIDAD, MIN, MAX);
        System.out.println("Input: " + Arrays.toString(valores));

        ArrayListEstadisticas estadisticas = new ArrayListEstadisticas();
        for (int valor : valores) {
            estadisticas.add((double) valor);
        }

        System.out.printf("Minimo: %f\n", estadisticas.minimo());
        System.out.printf("Maximo: %f\n", estadisticas.maximo());
        System.out.printf("Sumatorio: %f\n", estadisticas.sumatorio());
        System.out.printf("Media: %f\n", estadisticas.media());
        System.out.printf("Moda: %f\n", estadisticas.moda());
        System.out.printf("Moda2: %f\n", estadisticas.moda2());
    }

    public static void main(String[] args) {
        Ejercicio2 ejercicio2 = new Ejercicio2();
        ejercicio2.execute();
    }
}
