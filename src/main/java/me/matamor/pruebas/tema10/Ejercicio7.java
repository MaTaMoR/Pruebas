package me.matamor.pruebas.tema10;

import me.matamor.pruebas.lib.Input;

import java.util.HashMap;
import java.util.Map;

public class Ejercicio7 {

    private static final int CONVERTIR = 1;
    private static final int SALIR = 0;

    private static final int MIN_DIVISA = 3;
    private static final int MAX_DIVISA = 3;

    private static final double MIN_VALOR = 1;

    private final Map<String, Double> conversiones;

    public Ejercicio7() {
        this.conversiones = new HashMap<>();

        this.conversiones.put("USD", 1.21);
        this.conversiones.put("GBP", 0.87);
        this.conversiones.put("INR", 89.26);
        this.conversiones.put("AUD", 1.56);
        this.conversiones.put("CAD", 1.53);
        this.conversiones.put("ARS", 108.54);
        this.conversiones.put("BOB", 8.33);
        this.conversiones.put("CLP", 878.26);
        this.conversiones.put("VEZ", 4404.64); //Se llama COP
        this.conversiones.put("CRC", 739.18);
        this.conversiones.put("DOP", 69.86);
        this.conversiones.put("MEX", 25.25);
    }

    private static class ConversionException extends Exception {

        public ConversionException(String message) {
            super(message);
        }
    }

    private double convertir(double valor, String divisa) throws ConversionException {
        if (this.conversiones.containsKey(divisa)) {
            double conversionRate = this.conversiones.get(divisa);
            System.out.printf("Conversion rate: %.2f\n", conversionRate);

            return valor * conversionRate;
        } else {
            throw new ConversionException("La divisa '" + divisa + "' no está registrada!");
        }
    }

    private void convertirDivisa() {
        System.out.print("Introduce la divisa a convertir: ");
        String divisa = Input.leerLinea(MIN_DIVISA, MAX_DIVISA);

        System.out.print("Introduce el valor a convertir: ");
        double valor = Input.leerDouble(MIN_VALOR, -1);

        try {
            double resultado = convertir(valor, divisa);
            System.out.printf("Resultado: %.2f\n", resultado);
        } catch (ConversionException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private int mostrarMenu() {
        do {
            System.out.println("Divisas: " + this.conversiones.keySet());
            System.out.println(CONVERTIR + ". Convertir euro a divisa.");
            System.out.println(SALIR + ". Salir de la aplicación.");

            System.out.print("\nIntroduce una opción: ");

            int opcion = Input.leerInt();

            switch (opcion) {
                case CONVERTIR:
                case SALIR: {
                    return opcion;
                }
                default: {
                    System.out.println("La opción introducida es invalida!");
                    break;
                }
            }
        } while (true);
    }

    public void execute() {
        int opcion;

        do {
            opcion = mostrarMenu();

            switch (opcion) {
                case CONVERTIR: {
                    convertirDivisa();
                    Input.esperarEnter();
                }
                case SALIR: {
                    System.out.println("Saliendo de la aplicación...");
                }
            }
        } while (opcion != SALIR);
    }

    public static void main(String[] args) {
        Map<String, String> mao = new HashMap<>();
        mao.put("A", "B");

        System.out.println(mao);
        for (String string : mao.keySet()) {
            if (string.equals("A")) {
                mao.remove("A");
            }
        }
        System.out.println(mao);
        Ejercicio7 ejercicio7 = new Ejercicio7();
        ejercicio7.execute();
    }
}
