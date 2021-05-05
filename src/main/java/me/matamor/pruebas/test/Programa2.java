package me.matamor.pruebas.test;

import java.util.Scanner;

public class Programa2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int MINIMO = 1; //El valor minimo que se puede introducir, es para que no introduzcan negativos

        System.out.println("Introduce un texto:");
        String texto = scanner.nextLine(); //No hay verificacion para el texto, asi que de esta forma esta bien

        int numero = preguntarNumero(scanner, MINIMO); //El numero necesita verificacion, paso el scanner para no recrearlo

        int palabras = cuentaPalabras(texto); //Calculamos las palabras del texto
        String multiplo = posMultiplo(texto, numero); //Cogemos los caracteres multiplos del texto
        String mayus = paresMayus(texto); //Convertimos en mayusculas los pares y impares en minisculas

        //Mostramos los resultados
        System.out.printf("%-10s %,d\n", "Palabras:", palabras);
        System.out.printf("%-10s %s\n", "Multiplo:", multiplo);
        System.out.printf("%-10s %s\n", "Mayus:", mayus);
    }

    private static int preguntarNumero(Scanner scanner, int minimo) {
        while (true) { //Se ejecutara el bucle hasta que se introduzca un valor valido o se cierre el programa
            System.out.printf("Introduce un numero mayor o igual que %d!\n", minimo);

            try {
                int numero = Integer.parseInt(scanner.nextLine()); //Leemos el numero introducido

                if (numero < minimo) { //Comprobamos que el numero introducido no sea menor que el minimo
                    System.out.printf("El numero introducido es menor que %d!", minimo);
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) { //Comprobamos que el valor introducido sea un numero
                System.out.println("El valor introducido no es un numero!");
            }
        }
    }

    private static int cuentaPalabras(String texto) {
        String[] partes = texto.split(" "); //Dividimos el texto por el espacio
        return partes.length; //Devolvemos la cantidad de partes que hay (palabras)
    }

    private static String posMultiplo(String texto, int numero) {
        char[] caracteres = texto.toCharArray(); //Dividimos el texto en caracteres
        StringBuilder stringBuilder = new StringBuilder(); //Usaremos un StringBuilder para juntar los caracteres

        for (int i = 0; caracteres.length > i; i++) {
            if (i % numero == 0) { //Comprobamos si la posicion del caracter es multiplo del numero
                stringBuilder.append(caracteres[i]); //Añadimos el caracter al stringbuilder
            }
        }

        return stringBuilder.toString(); //Devolvemos el texto
    }

    private static String paresMayus(String texto) {
        char[] caracteres = texto.toCharArray(); //Dividimos el texto en caracteres

        for (int i = 0; caracteres.length > i; i++) {
            if (i % 2 == 0) { //Comprobamos si la posicion es par
                caracteres[i] = Character.toUpperCase(caracteres[i]); //Asignamos el caracter en mayuscula a la posicion
            } else {
                caracteres[i] = Character.toLowerCase(caracteres[i]); //Asignamos el caracter en minuscula a la posicion
            }
        }

        return new String(caracteres); //Creamos el texto a partir de los caracteres
    }
}
