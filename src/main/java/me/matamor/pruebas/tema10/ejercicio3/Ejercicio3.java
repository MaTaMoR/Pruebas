package me.matamor.pruebas.tema10.ejercicio3;

import me.matamor.pruebas.lib.Randomizer;

import java.util.Arrays;

public class Ejercicio3 {

    public void execute() {
        final int CANTIDAD = 10;

        String[] input = Randomizer.randomStringArray(CANTIDAD);
        System.out.println("Input: " + Arrays.toString(input));

        IPila<String> pila = new ArrayListPila<>();
        for (String string : input) {
            System.out.println("Push: " + string);
            pila.push(string);
        }

        while (!pila.isEmpty()) {
            System.out.println("Top: " + pila.top());
            System.out.println("Pop: " + pila.pop());
        }
    }

    public static void main(String[] args) {
        Ejercicio3 ejercicio3 = new Ejercicio3();
        ejercicio3.execute();
    }
}
