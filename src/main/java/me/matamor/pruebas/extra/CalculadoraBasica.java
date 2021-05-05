package me.matamor.pruebas.extra;

import java.util.Scanner;

public class CalculadoraBasica {

    public interface Operacion {

        String getNombre();

        String getDescripcion();

        double realizarOperacion(double primerValor, double segundoValor) throws OperacionException;

        class OperacionException extends Exception {

            public OperacionException(String error) {
                super(error);
            }
        }
    }

    public abstract static class OperacionSimple implements Operacion {

        private final String nombre;
        private final String descripcion;

        public OperacionSimple(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        @Override
        public String getNombre() {
            return this.nombre;
        }

        @Override
        public String getDescripcion() {
            return this.descripcion;
        }
    }

    public static void main(String[] args) {
        final int CODIGO_SUMA = 0; //Codigo de suma
        final int CODIGO_RESTA = 1; //Codigo de resta
        final int CODIGO_MULTIPLICACION = 2; //Codigo de multiplicacion
        final int CODIGO_DIVISION = 3; //Codigo de division
        final int CODIGO_RESTO_DIVISION = 4; //Codigo de resto division
        final int CODIGO_SALIDA = 5; //Codigo de salida

        final int OPERACIONES = 5; //Total de operaciones

        final int CODIGO_MINIMO = 0; //El codigo minimo que puede ser introducido
        final int CODIGO_MAXIMO = 6; //El codigo maximo que puede ser introducido

        final int DECIMALES_MAXIMOS = 4; //Decimales maximos que se mostraran

        //Creamos toda la informacion de la calculadora
        Operacion[] operaciones = new Operacion[OPERACIONES];

        //Creamos la opercion de suma
        operaciones[CODIGO_SUMA] = new OperacionSimple("Suma", "Suma dos numeros") {
            @Override
            public double realizarOperacion(double primerValor, double segundoValor) throws OperacionException {
                return primerValor + segundoValor;
            }
        };

        //Creamos la operacion de resta
        operaciones[CODIGO_RESTA] = new OperacionSimple("Resta", "Resta dos numeros") {
            @Override
            public double realizarOperacion(double primerValor, double segundoValor) throws OperacionException {
                return primerValor - segundoValor;
            }
        };

        //Creamos la operacion de multiplicacion
        operaciones[CODIGO_MULTIPLICACION] = new OperacionSimple("Multiplicacion", "Multiplica dos numeros") {
            @Override
            public double realizarOperacion(double primerValor, double segundoValor) throws OperacionException {
                return primerValor * segundoValor;
            }
        };

        //Creamos la operacion de division
        operaciones[CODIGO_DIVISION] = new OperacionSimple("Division", "Divide dos numeros") {
            @Override
            public double realizarOperacion(double primerValor, double segundoValor) throws OperacionException {
                if (segundoValor == 0) {
                    throw new OperacionException("No se puede dividir entre cero!");
                } else {
                    return primerValor / segundoValor;
                }
            }
        };

        //Creamos la operacion de resto de division
        operaciones[CODIGO_RESTO_DIVISION] = new OperacionSimple("Resto Division", "Resto de dividir dos numeros") {
            @Override
            public double realizarOperacion(double primerValor, double segundoValor) throws OperacionException {
                if (segundoValor == 0) {
                    throw new OperacionException("No se puede dividir entre cero!");
                } else {
                    return primerValor % segundoValor;
                }
            }
        };

        Scanner scanner = new Scanner(System.in);
        boolean calculadoraActiva = true;

        do {
            //Print operaciones
            System.out.println("----------------------------------------------------");

            for (int i = 0; operaciones.length > i; i++) {
                Operacion operacion = operaciones[i];
                //Muestra la operacion con el formato 'codigo - nombre: descripcion'
                System.out.printf("%d - %-15s: %s\n", i, operacion.getNombre(), operacion.getDescripcion());
            }

            System.out.println("----------------------------------------------------");
            System.out.printf("%d - %-15s: %s", CODIGO_SALIDA, "Salir", "Salir de la calculadora\n");

            int codigoOperacion = leerInt(scanner);

            if (codigoOperacion < CODIGO_MINIMO || codigoOperacion > CODIGO_MAXIMO) {
                System.out.printf("El codigo de operacion '%s' es invalido!\n", codigoOperacion);
            } else {
                if (codigoOperacion == CODIGO_SALIDA) { //Comprobamos si el codigo es de salida
                    System.out.println("Saliendo de la calculadora...");
                    calculadoraActiva = false;
                } else {
                    Operacion operacion = operaciones[codigoOperacion];
                    System.out.printf("Operacion de '%s' seleccionada!\n", operacion.getNombre()); //Mostramos la operacion seleccionada

                    System.out.println("Introduce el primer numero:");
                    double primerNumero = leerDouble(scanner); //Leemos el primero numero de la operacion

                    System.out.println("Introduce el segundo numero:");
                    double segundoNumero = leerDouble(scanner);  //Leemos el segundo numero de la operacion

                    try {
                        double resultado = operacion.realizarOperacion(primerNumero, segundoNumero);

                        int decimales = 0;

                        //Calculamos la parte decimal de el numero, lo combertimos a String y lo partimos por la coma
                        //si hay 2 partes es que tiene parte decimal y usamos la parte decimal como tamaño de decimales
                        //el problema es que si tu lengua esta en ingles es posible que no funcione ya que se usa punto
                        String[] partes = String.valueOf(resultado).split(",");
                        if (partes.length == 2) {
                            decimales = partes[1].length();
                        }

                        //Si los decimales son mas que el maximo asignamos el maximo
                        if (decimales > DECIMALES_MAXIMOS) {
                            decimales = DECIMALES_MAXIMOS;
                        }

                        //Mostramos el el resultado teniendo en cuenta si es un int o double
                        System.out.printf("Resultado de %s:\n", operacion.getNombre());
                        if (decimales == 0) {
                            System.out.printf("%d\n", (int) resultado);
                        } else {
                            System.out.printf("%." + decimales + "f\n", resultado);
                        }
                        //System.out.printf((decimales == 0 ? "%d" : "%." + decimales + "f") + "\n", operacion.getNombre(), (decimales == 0 ? (int) resultado : resultado));

                        System.out.println("Pulsa INTRO para continuar!");
                        scanner.nextLine();
                    } catch (Operacion.OperacionException e) {
                        System.out.println("Error de operacion: " + e.getMessage());
                    }
                }
            }
        } while (calculadoraActiva);

        System.out.println("BYE!");
    }

    public static int leerInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("El número introducido es invalido!");
            }
        }
    }

    public static double leerDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("El número introducido es invalido!");
            }
        }
    }
}
