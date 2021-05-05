package me.matamor.pruebas.tema9;

import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        solicitarNumeros();
    }

    public static void solicitarNumeros() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;

        do {
            try {
                numero = Integer.parseInt(scanner.nextLine());
                System.out.println("Has introducido el número: " + numero);
            } catch (NumberFormatException e) {
                System.out.println("El valor introducido no es un número valido!");
            }
        } while (numero >= 0);
    }
}
