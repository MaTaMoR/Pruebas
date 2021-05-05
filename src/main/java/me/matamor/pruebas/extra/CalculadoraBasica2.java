package me.matamor.pruebas.extra;

import java.util.Scanner;

public class CalculadoraBasica2 {

    public static void main(String[] args) {
        final int CODIGO_SUMA = 0; //Codigo de suma
        final int CODIGO_RESTA = 1; //Codigo de resta
        final int CODIGO_MULTIPLICACION = 2; //Codigo de multiplicacion
        final int CODIGO_DIVISION = 3; //Codigo de division
        final int CODIGO_RESTO_DIVISION = 4; //Codigo de resto division
        final int CODIGO_SALIDA = 5; //Codigo de salida

        final int CODIGO_MINIMO = 0; //El codigo minimo que puede ser introducido
        final int CODIGO_MAXIMO = 6; //El codigo maximo que puede ser introducido

        final int DECIMALES_MAXIMOS = 4; //Decimales maximos que se mostraran
		
        Scanner scanner = new Scanner(System.in);
        boolean calculadoraActiva = true;

        do {
            //Print operaciones
            System.out.println("----------------------------------------------------");
            System.out.println(CODIGO_SUMA + " - Suma");
            System.out.println(CODIGO_RESTA + " - Resta");
            System.out.println(CODIGO_MULTIPLICACION + " - Multiplicacion");
            System.out.println(CODIGO_DIVISION + " - Division");
            System.out.println(CODIGO_RESTO_DIVISION + " - Resto division");
            System.out.println("----------------------------------------------------");
            System.out.println(CODIGO_SALIDA + " - Salir");

            int codigoOperacion = leerInt(scanner);

            if (codigoOperacion < CODIGO_MINIMO || codigoOperacion > CODIGO_MAXIMO) {
                System.out.printf("El codigo de operacion '%s' es invalido!\n", codigoOperacion);
            } else {
                if (codigoOperacion == CODIGO_SALIDA) { //Comprobamos si el codigo es de salida
                    System.out.println("Saliendo de la calculadora...");
                    calculadoraActiva = false;
                } else {
                    System.out.println("Introduce el primer numero:");
                    double primerNumero = leerDouble(scanner); //Leemos el primero numero de la operacion

                    System.out.println("Introduce el segundo numero:");
                    double segundoNumero = leerDouble(scanner);  //Leemos el segundo numero de la operacion

					if (segundoNumero == 0 && (codigoOperacion == CODIGO_DIVISION || codigoOperacion == CODIGO_RESTO_DIVISION)) {
						System.out.println("No se puede dividir por cero!");
					} else {
						double resultado = 0;
						
						switch (codigoOperacion) {
							case CODIGO_SUMA: {
								resultado = primerNumero + segundoNumero;
								break;
							}
							case CODIGO_RESTA: {
								resultado = primerNumero - segundoNumero;
								break;
							}
							case CODIGO_MULTIPLICACION: {
								resultado = primerNumero * segundoNumero;
								break;
							}
							case CODIGO_DIVISION: {
								resultado = primerNumero / segundoNumero;
								break;
							}
							case CODIGO_RESTO_DIVISION: {
								resultado = primerNumero % segundoNumero;
								break;
							}
						}

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
						System.out.println("Resultado:");
						
						if (decimales == 0) {
							System.out.printf("%d\n", (int) resultado);
						} else {
							System.out.printf("%." + decimales + "f\n", resultado);
						}
						
						//System.out.println((decimales == 0 ? "%d" : "%." + decimales + "s") + "\n", (decimales == 0 ? (int) resultado : resultado));
					}
					
					System.out.println("Pulsa INTRO para continuar!");
					scanner.nextLine();
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
