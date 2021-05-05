package me.matamor.pruebas.tema10;

import me.matamor.pruebas.lib.Randomizer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {

    public static void main(String[] args) {
        final int MIN = 0;
        final int MAX = 50;
        final int CANTIDAD = 20;

        int[] array = Randomizer.randomIntArray(CANTIDAD, MIN, MAX);
        System.out.println("Input:" + Arrays.toString(array));

        List<Integer> sorted = paresDespuesImpares(array);
        System.out.println("Output:" + sorted);
    }

    public static List<Integer> paresDespuesImpares(int[] array) {
        List<Integer> numeros = Arrays.stream(array).boxed().collect(Collectors.toList());

        Comparator<Integer> comparator = (o1, o2) -> {
            boolean par1 = o1 % 2 == 0;
            boolean par2 = o2 % 2 == 0;

            return par1 == par2 ? (o1.compareTo(o2)) : par2 ? 1 : -1;
        };

        numeros.sort(comparator);

        return numeros;
    }
}
