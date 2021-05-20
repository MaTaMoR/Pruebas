package me.matamor.pruebas.tema12.ejercicio13;

import me.matamor.pruebas.lib.FileUtils;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalculadoraPrimos extends Thread {

    private final File output;
    private final AtomicBoolean activo;

    public CalculadoraPrimos(File output) {
        this.output = output;
        this.activo = new AtomicBoolean(false);
    }

    private void guardarNumero(int numero) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.output, true))) {
            bufferedWriter.write(numero + FileUtils.LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int ultimoNumeroPrimo() {
        int ultimoNumero = -1;

        if (this.output.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.output))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        int numero = Integer.parseInt(line);
                        if (numero > ultimoNumero && esPrimo(numero)) {
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

    public boolean esPrimo(int numero) {
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

    public void parar() {
        if (this.activo.get()) {
            this.activo.set(false);
        }
    }

    @Override
    public void run() {
        this.activo.set(true);

        int ultimoNumero = ultimoNumeroPrimo();
        if (ultimoNumero == -1) {
            ultimoNumero = 1;
        }

        int ultimoPrimo = -1;

        System.out.println("Empezando por: " + ultimoNumero);

        while (this.activo.get() && ultimoNumero < Integer.MAX_VALUE) {
            if (esPrimo(ultimoNumero)) {
                guardarNumero(ultimoNumero);
                ultimoPrimo = ultimoNumero;
            }

            //System.out.println("Primo encontrado: " + ultimoNumero);
            ultimoNumero++;
        }

        System.out.println("Terminando con: " + ultimoPrimo);
    }
}
