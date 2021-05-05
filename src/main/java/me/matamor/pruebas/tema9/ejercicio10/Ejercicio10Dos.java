package me.matamor.pruebas.tema9.ejercicio10;

import me.matamor.pruebas.lib.Randomizer;

public class Ejercicio10Dos {

    private static final int CODIGO_SUMA = 0; //Codigo de suma
    private static final int CODIGO_RESTA = 1; //Codigo de resta
    private static final int CODIGO_MULTIPLICACION = 2; //Codigo de multiplicacion
    private static final int CODIGO_DIVISION = 3; //Codigo de division
    private static final int CODIGO_RESTO_DIVISION = 4; //Codigo de resto division

    private static final char OPERADOR_SUMA = '+';
    private static final char OPERADOR_RESTA = '-';
    private static final char OPERADOR_MULTIPLICACION = '*';
    private static final char OPERADOR_DIVISION = '/';
    private static final char OPERADOR_RESTO_DIVISION = '%';

    private static final int DECIMALES_MAXIMOS = 4; //Decimales maximos que se mostraran

    private void ejecutar() {
        final int CANTIDAD_OPERACIONES = 10;
        final double MIN_VALUE = -100;
        final double MAX_VALUE = 100;

        int[] operaciones = new int[] { CODIGO_SUMA, CODIGO_RESTA, CODIGO_MULTIPLICACION, CODIGO_DIVISION, CODIGO_RESTO_DIVISION };
        char[] operadores = new char[operaciones.length];
        operadores[CODIGO_SUMA] = OPERADOR_SUMA;
        operadores[CODIGO_RESTA] = OPERADOR_RESTA;
        operadores[CODIGO_MULTIPLICACION] = OPERADOR_MULTIPLICACION;
        operadores[CODIGO_DIVISION] = OPERADOR_DIVISION;
        operadores[CODIGO_RESTO_DIVISION] = OPERADOR_RESTO_DIVISION;

        for (int i = 0; CANTIDAD_OPERACIONES > i; i++) {
            int random = Randomizer.randomInt(0, operaciones.length - 1);
            int operacion = operaciones[random];

            double primerNumero = Randomizer.randomDouble(MIN_VALUE, MAX_VALUE);
            double segundoNumero = Randomizer.randomDouble(MIN_VALUE, MAX_VALUE);

            try {
                double resultado = realizarOperacion(operacion, primerNumero, segundoNumero);
                System.out.printf("%s %s %s = %s", format(primerNumero), operadores[operacion], format(segundoNumero), format(resultado));
            } catch (MathException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String format(double value) {
        int decimales = 0;

        String[] partes = String.valueOf(value).split(",");
        if (partes.length == 2) {
            decimales = partes[1].length();
        }

        if (decimales > DECIMALES_MAXIMOS) {
            decimales = DECIMALES_MAXIMOS;
        }

        return String.format("%." + decimales + "f", value);
    }

    private double realizarOperacion(int codigoOperacion, double primerNumero, double segundoNumero) throws MathException {
        if (segundoNumero == 0 && (codigoOperacion == CODIGO_DIVISION || codigoOperacion == CODIGO_RESTO_DIVISION)) {
            throw new MathException("No se puede dividir por cero!");
        } else {
            double resultado;

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
                default: {
                    throw new MathException("La operacion introducida no existe: " + codigoOperacion);
                }
            }


            //Mostramos el el resultado teniendo en cuenta si es un int o double
            return resultado;
        }
    }

    public static void main(String[] args) {
        Ejercicio10Dos ejercicio10dos = new Ejercicio10Dos();
        ejercicio10dos.ejecutar();
    }
}
