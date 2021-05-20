package me.matamor.pruebas.tema12.ejercicio13;

import me.matamor.pruebas.lib.FileUtils;
import me.matamor.pruebas.lib.Input;

import java.io.File;

public class Ejercicio13 {

    public static void main(String[] args) {
        File output = new File(FileUtils.CARPETA_PRUEBAS, "primo.txt");
        CalculadoraPrimos calculadoraPrimos = new CalculadoraPrimos(output);
        calculadoraPrimos.start();

        String input;
        do {
            input = Input.leerLinea();
        } while (!input.equals("stop"));

        calculadoraPrimos.parar();

        System.out.println("Saliendo!");
    }
}
