package me.matamor.pruebas.tema9.ejercicio10;

import me.matamor.pruebas.lib.Randomizer;

public class Ejercicio10 {

    private static final int CODIGO_SUMA = 0; //Codigo de suma
    private static final int CODIGO_RESTA = 1; //Codigo de resta
    private static final int CODIGO_MULTIPLICACION = 2; //Codigo de multiplicacion
    private static final int CODIGO_DIVISION = 3; //Codigo de division
    private static final int CODIGO_RESTO_DIVISION = 4; //Codigo de resto division

    private static final int DECIMALES_MAXIMOS = 4; //Decimales maximos que se mostraran

    private void ejecutar() {
        final int CANTIDAD_OPERACIONES = 10;

        for (int i = 0; CANTIDAD_OPERACIONES > i; i++) {
            try {
                realizarOperacion();
            } catch (MathException e) {
                System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(2500);
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

    private String nextString() {
        String valores = "012H345F678E9";
        int random = Randomizer.randomInt(0, valores.length() - 1);

        return String.valueOf(valores.charAt(random));
    }

    public int nextInt() {
        do {
            String input = nextString(); //scanner.nextLine()
            System.out.println("Input: " + input);

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("El input " + input + " no es un numero valido!");
            }
        } while (true);
    }

    private double realizarOperacion() throws MathException {
        System.out.println("\n******** NUEVA OPERACIÓN ********");
        int codigoOperacion = nextInt();

        double resultado;

        switch (codigoOperacion) {
            case CODIGO_SUMA: {
                System.out.println("\n******** SUMA ********");

                double primerNumero = nextInt();
                double segundoNumero = nextInt();
                resultado = primerNumero + segundoNumero;

                System.out.printf("%s + %s = %s\n", format(primerNumero), format(segundoNumero), format(resultado));
                System.out.println("******** SUMA ********\n");
                break;
            }
            case CODIGO_RESTA: {
                System.out.println("\n******** RESTA ********");

                double primerNumero = nextInt();
                double segundoNumero = nextInt();
                resultado = primerNumero + segundoNumero;

                System.out.printf("%s - %s = %s\n", format(primerNumero), format(segundoNumero), format(resultado));
                System.out.println("******** RESTA ********\n");
                break;
            }
            case CODIGO_MULTIPLICACION: {
                System.out.println("\n******** MULTIPLICACION ********");

                double primerNumero = nextInt();
                double segundoNumero = nextInt();
                resultado = primerNumero + segundoNumero;

                System.out.printf("%s * %s = %s\n", format(primerNumero), format(segundoNumero), format(resultado));
                System.out.println("******** MULTIPLICACION ********\n");
                break;
            }
            case CODIGO_DIVISION: {
                System.out.println("\n******** DIVISION ********");

                double primerNumero = nextInt();
                double segundoNumero = nextInt();
                if (segundoNumero == 0)
                    throw new MathException("No se puede dividir por 0!");

                resultado = primerNumero + segundoNumero;

                System.out.printf("%s / %s = %s\n", format(primerNumero), format(segundoNumero), format(resultado));
                System.out.println("******** DIVISION ********\n");
                break;
            }
            case CODIGO_RESTO_DIVISION: {
                System.out.println("\n******** RESTO DIVISION ********");

                double primerNumero = nextInt();
                double segundoNumero = nextInt();
                if (segundoNumero == 0)
                    throw new MathException("No se puede dividir por 0!");

                resultado = primerNumero + segundoNumero;

                System.out.printf("%s %% %s = %s\n", format(primerNumero), format(segundoNumero), format(resultado));
                System.out.println("******** RESTO DIVISION ********\n");
                break;
            }
            default: {
                throw new MathException("La operacion introducida no existe: " + codigoOperacion);
            }
        }

        //Mostramos el el resultado teniendo en cuenta si es un int o double
        return resultado;
    }

    public static void main(String[] args) {
        Ejercicio10 ejercicio10 = new Ejercicio10();
        ejercicio10.ejecutar();
    }
}
