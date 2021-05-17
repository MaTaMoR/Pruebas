package me.matamor.pruebas.tema12;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;

public class Ejercicio13 {

    private static final File numerosPrimos = new File(FileUtils.CARPETA_PRUEBAS, "numeros_primos.txt");

    public static void main(String[]args) {
        calcularPrimos();
    }

    public static void guardarNumeroPrimo(int numero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numerosPrimos))) {
            writer.write(String.valueOf(numero));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int ultimoNumeroPrimo() {
        int ultimoNumero = -1;

        if (numerosPrimos.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(numerosPrimos))) {
                String line = reader.readLine();
                if (line != null) {
                    try {
                        int numero = Integer.parseInt(line);
                        if (esPrimo(numero)) {
                            ultimoNumero = numero;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ultimoNumero;
    }

    public static void calcularPrimos() {
        int numeroActual = ultimoNumeroPrimo();
        if (numeroActual == -1) {
            numeroActual = 1;
        }

        while (numeroActual < Integer.MAX_VALUE) {
            numeroActual++;

            if (esPrimo(numeroActual)) {
                guardarNumeroPrimo(numeroActual);

                System.out.println("Primo encontrado: " + numeroActual);
            }
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero == 2 || numero == 3) {
            return true;
        } else if (numero % 2 == 0) {
            return false;
        } else {
            int raizCuadrada = (int) Math.sqrt(numero) + 1;
            for (int i = 3; raizCuadrada > i; i += 2) {
                if (numero % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
