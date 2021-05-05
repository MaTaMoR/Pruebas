package me.matamor.pruebas.tema9;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        int[] numeros = leerNumeros();

        if (numeros.length == 0) {
            System.out.println("No se han introducido numeros!");
        } else {
            int mayorNumero = numeros[0];

            for (int i = 1; numeros.length > i; i++) {
                if (numeros[i] > mayorNumero) {
                    mayorNumero = numeros[i];
                }
            }

            System.out.println("Valores: " + Arrays.toString(numeros));
            System.out.println("El numero mayor es: " + mayorNumero);
        }
    }

    public static int[] leerNumeros() {
        Scanner scanner = new Scanner(System.in);

        final int CANTIDAD = 10;

        int[] numeros = new int[CANTIDAD];
        int contador = 0;
        boolean continuar = true;

        do {
            try {
                int numero = Integer.parseInt(scanner.nextLine());

                if (contador >= numeros.length) {
                    int[] auxNumeros = new int[numeros.length + CANTIDAD];
                    for (int i = 0; numeros.length > i; i++) {
                        auxNumeros[i] = numeros[i];
                    }

                    numeros = auxNumeros;
                }

                numeros[contador++] = numero;
            } catch (NumberFormatException e) {
                continuar = false;
            }
        } while (continuar);

        return Arrays.copyOfRange(numeros, 0, contador);
    }
}
