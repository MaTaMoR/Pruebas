package me.matamor.pruebas.tema10.ejercicio4;

import me.matamor.pruebas.lib.Randomizer;

import java.util.Arrays;

public class Ejercicio4 {

    public void execute() {
        final int CANTIDAD = 10;

        String[] palabras = Randomizer.randomStringArray(CANTIDAD);
        System.out.println("Input: " + Arrays.toString(palabras));

        Cola<String> cola = new Cola<>();

        System.out.println("\nAñadiendo:\n");
        for (String string : palabras) {
            cola.add(string);
            System.out.println(string);
        }

        System.out.println("\nQuitando:\n");
        for (int i = 0; (CANTIDAD / 2) > i; i++) {
            String valor = cola.remove();
            System.out.println(valor);
        }

        System.out.println("\nCola:\n");
        for (String string : cola.elementos()) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
        Ejercicio4 ejercicio4 = new Ejercicio4();
        ejercicio4.execute();
    }
}
