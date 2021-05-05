package me.matamor.pruebas.test;

import java.util.Random;
import java.util.Scanner;

public class Programa1 {

    public static void main(String[] args) {
        final double NOTA_MIN = 0.00; //Nota minima que se puede sacar
        final double NOTA_MAX = 10.00; //Nota maxima que se puede sacar
        final double NOTA_APROBADO = 5.00; //Nota para ser considerado aprobado

        int cantidad = preguntarCantidad(); //Preguntamos la cantidad de calificaciones a generar

        double calificacionMinima = 10; //En esta variable guardaremos la calificacion mas baja
        double calificacionMaxima = 0; //En esta variable guardaremos la calificacion mas alta

        double sumatorio = 0; //En esta variable guardaremos la suma total de las calificaciones
        int aprobados = 0; //En esta variable guardaremos la cantidad de aprobados

        System.out.printf("Generando los resultados de %,d calificaciones!\n", cantidad);

        //Calculamos las calificaciones la cantidad de veces introducida
        for (int i = 0; cantidad > i; i++) {
            double calificacion = random(NOTA_MIN, NOTA_MAX);

            //Sumamos la calificacion al total
            sumatorio += calificacion;

            if (calificacion < calificacionMinima) {
                calificacionMinima = calificacion;
            }

            if (calificacion > calificacionMaxima) {
                calificacionMaxima = calificacion;
            }

            if (calificacion >= NOTA_APROBADO) {
                aprobados++;
            }
        }

        double media = sumatorio / cantidad; //Calculamos la media de las calificaciones
        int suspendidos = cantidad - aprobados; //Calculamos la cantidad de suspendidos

        System.out.println("-----------------------------------");
        System.out.printf("%-20s %10s\n", "Calificacion minima:", formatDouble(calificacionMinima));
        System.out.printf("%-20s %10s\n", "Calificacion maxima:", formatDouble(calificacionMaxima));
        System.out.printf("%-20s %10s\n", "Calificacion media:", formatDouble(media));
        System.out.printf("%-20s %10s\n", "Suspendidos:", formatInt(suspendidos));
        System.out.printf("%-20s %10s\n", "Aprobados:", formatInt(aprobados));
        System.out.println("-----------------------------------");
    }

    public static String formatInt(int value) {
        return String.format("%,d", value);
    }

    public static String formatDouble(double value) {
        return String.format("%.2f", value);
    }

    public static double random(double min, double max) {
        Random random = new Random(); //Generamos un número aleatorio entre min y max
        return (min + (max - min)) * random.nextDouble();
    }
    
    public static int preguntarCantidad() {
        Scanner scanner = new Scanner(System.in);

        //La accion se ejecutara hasta que se introduzca un valor valido o se cierre el programa
        while (true) {
            System.out.println("Escribe la cantidad de calificaciones:");

            try {
                int cantidad = Integer.parseInt(scanner.nextLine());

                //Comprobamos que la cantidad introducida no es inferior a 1!
                if (cantidad < 1) {
                    System.out.println("La cantidad no puede ser inferior a 1!");
                } else {
                    return cantidad;
                }
            } catch (NumberFormatException e) {
                //Si el valor introducido no es un numero dara un error, por ello mostramos un mensaje
                System.out.println("El valor introducido no es un número valido!");
            }
        }
    }
}
