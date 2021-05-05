package me.matamor.pruebas.tema9;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        solicitarDecimales();
    }

    public static void solicitarDecimales() {
        Scanner scanner = new Scanner(System.in);
        double[] decimales = new double[10];

        double masGrande = Double.MIN_VALUE;
        int errores = 0;

        for (int i = 0; decimales.length > i; i++) {
            double numero = 0;
            boolean valido = false;

            do {
                try {
                    numero = Double.parseDouble(scanner.nextLine());
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("El valor introducido no es un decimal valido!");
                    errores++;
                }
            } while (!valido);

            if (numero > masGrande) {
                masGrande = numero;
            }

            decimales[i] = numero;
        }

        System.out.println("Valores: " + Arrays.toString(decimales));
        System.out.println("Valor más grande: " + masGrande);
        System.out.println("Errores: " + errores);
    }
}
