package me.matamor.pruebas.tema9;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {
        int[] array1 = new int[5];
        int[] array2 = null;

        leerNumeros(array1);
        leerNumeros(array2);

        leerNumeros2(array1);
        leerNumeros2(array2);

        leerNumeros3(array1);
        leerNumeros3(array2);
    }

    public static void leerNumeros(int[] array) {
        if (array == null) {
            System.out.println("El array es null!");
        } else {
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; array.length > i; i++) {
                boolean valido = false;
                int numero = 0;

                do {
                    try {
                        System.out.println("Escribe el numero para la posicion: " + i);
                        numero = Integer.parseInt(scanner.nextLine());
                        valido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("El valor introducido no es un numero!");
                    }
                } while (!valido);

                array[i] = numero;
            }

            System.out.println("Se ha llegado al tamaño máximo del array!");
            System.out.println("Contenido: " + Arrays.toString(array));
        }
    }

    public static void leerNumeros2(int[] array) {
        try {
            Scanner scanner = new Scanner(System.in);

            int contador = 0;

            do {
                try {
                    int numero = Integer.parseInt(scanner.nextLine());
                    array[contador++] = numero;
                } catch (NumberFormatException e) {
                    System.out.println("El valor introducido no es un numero!");
                }
            } while (contador < array.length);

            System.out.println("Se ha llegado al tamaño máximo del array!");
            System.out.println("Contenido: " + Arrays.toString(array));
        } catch (NullPointerException e) {
            System.out.println("El array es null!");
        }
    }

    public static void leerNumeros3(int[] array) {
        try {
            Scanner scanner = new Scanner(System.in);

            int contador = 0;

            do {
                try {
                    int numero = Integer.parseInt(scanner.nextLine());
                    array[contador++] = numero;
                } catch (NumberFormatException e) {
                    System.out.println("El valor introducido no es un numero!");
                }
            } while (true);
        } catch (NullPointerException e) {
            System.out.println("El array es null!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Se ha llegado al tamaño máximo del array!");
            System.out.println("Contenido: " + Arrays.toString(array));
        }
    }
}
