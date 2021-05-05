package me.matamor.pruebas.test.tiempo;

import me.matamor.pruebas.test.tiempo.colores.Color;
import me.matamor.pruebas.test.tiempo.colores.ColorBuilder;
import me.matamor.pruebas.test.tiempo.colores.Formato;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ReporteTiempo {

    public static void main(String[] args) {
        //Simular input para no tener que escribir tanto!

        String texto = "Valencia\n8/10/2018\n8:24:3\n22\n29.44\n1018.2\n80\n12";
        Scanner scanner = new Scanner(new ByteArrayInputStream(texto.getBytes()));

        final int ROJO = 1;
        final int AMARILLO = 3;
        final int VERDE = 2;
        final int AZUL = 4;
        final int MAGENTA = 5;

        final int ESPACIOS = 22;

        final double MINIMO_TEMPERATURA = -273.15;
        final int MINIMO_VELOCIDAD = 0;
        final int MINIMO_PRESION = 0;
        final int MINIMO_PROBABILIDAD = 0;
        final int MINIMO_RADIACION = 0;

        final int MAXIMO_PROBABILIDAD = 100;

        //Leemos la fecha
        System.out.println("Introduce la ciudad:");
        String ciudad = scanner.nextLine();

        //Leemos la fecha
        int[] fecha = leerFecha(scanner, "Introduce la fecha: dia/mes/año");

        //Leemos la hora
        int[] hora = leerHora(scanner, "Introduce la hora de la medida: hora:minuto:segundo");

        //Leemos la velocidad del viento
        int velocidadViento = leerInt(scanner, "Introduce la velocidad del viento:", MINIMO_VELOCIDAD, -1);

        //Leemos la temperatura
        double temperatura = leerDouble(scanner, "Introduce la temperatura:", MINIMO_TEMPERATURA, -1);

        //Leemos la presión atmosférica
        double presionAtmosferica = leerDouble(scanner, "Introduce la presión atmosférica:", MINIMO_PRESION, -1);

        //Leemos la probabilidad de lluvia
        int probabilidadDeLluvia = leerInt(scanner, "Introduce la probabilidad de lluvia (0-100)", MINIMO_PROBABILIDAD, MAXIMO_PROBABILIDAD);

        //Leemos el indice de radiacción ultravioleta
        int radiaccionUvi = leerInt(scanner, "Introduce la radiacción ultravioleta:", MINIMO_RADIACION, -1);

        //Ya hemos leído todos los valores necesarios, ahora mostraremos la información!

        //Mostramos el encabezado
        System.out.println(new ColorBuilder().formato(Formato.BOLD).color(Color.GRIS).fondo(Color.AZUL).build("** DATOS ESTACIÓN METEREOLÓGICA **"));

        ColorBuilder colorBuilder = new ColorBuilder();

        //Mostramos la ciudad
        System.out.printf("%-" + ESPACIOS + "s %s\n", "Ciudad:", ciudad);

        //Mostramos la fecha
        System.out.printf("%-" + ESPACIOS + "s %02d/%02d/%04d\n", "Fecha:", fecha[0], fecha[1], fecha[2]);

        //Mostramos la hora
        System.out.printf("%-" + ESPACIOS + "s %02d:%02d:%02d\n", "Hora de la mesura:", hora[0], hora[1], hora[2]);

        //Mostramos la velocidad del tiempo
        Color colorViento = (velocidadViento > 60 ? Color.ROJO : (velocidadViento >= 30 ? Color.AMARILLO : Color.VERDE));
        System.out.printf("%-" + ESPACIOS + "s %s\n", "Velocidad viento:", colorBuilder.color(colorViento).build(velocidadViento));

        //Mostramos la temperatura
        Color colorTemperatura = (temperatura > 35 ? Color.ROJO : (temperatura >= 27 ? Color.AMARILLO : (temperatura >= 22 ? Color.VERDE : Color.AZUL)));
        System.out.printf("%-" + ESPACIOS + "s %s Cº\n", "Temperatura:", colorBuilder.color(colorTemperatura).build(temperatura));

        //Mostramos la presión atmosférica
        System.out.printf("%-" + ESPACIOS + "s %.1f hPa\n", "Presión atmosférica:", presionAtmosferica);

        //Mstramos la lluvia
        Color colorLluvia = (probabilidadDeLluvia > 70 ? Color.ROJO : (probabilidadDeLluvia >= 35 ? Color.AMARILLO : Color.VERDE));
        System.out.printf("%-" + ESPACIOS + "s %s\n", "Probabilidad lluvia:", colorBuilder.color(colorLluvia).build(probabilidadDeLluvia));

        //Mostramos la radiacion ultravioleta
        Color colorUvi = (radiaccionUvi > 10 ? Color.MAGENTA : (radiaccionUvi >= 8 ? Color.ROJO : (radiaccionUvi >= 6 ? Color.AMARILLO : (radiaccionUvi >= 3 ? Color.AZUL : Color.VERDE))));
        System.out.printf("%-" + ESPACIOS + "s %s\n", "UVI:", colorBuilder.color(colorUvi).build(radiaccionUvi));
    }

    private static boolean esBisiesto(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }

        return false;
    }

    private static int diasDelMes(int mes, int year) {
        switch (mes) {
            case 1: //Enero
                return 31;
            case 2: //Febrero
                return (esBisiesto(year) ? 29 : 28);
            case 3: //Marzo
                return 31;
            case 4: //Abril
                return 30;
            case 5: //Mayo
                return 31;
            case 6: //Junio
                return 30;
            case 7: //Julio
                return 31;
            case 8: //Agosto
                return 31;
            case 9: //Septiembre
                return 30;
            case 10: //Octubre
                return 31;
            case 11: //Noviembre
                return 30;
            case 12: //Diciembre
                return 31;
            default:
                throw new IllegalArgumentException("El mes es menor que 1 o mayor que 12!");
        }
    }

    private static int[] leerFecha(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);

            String fechaBruta = scanner.nextLine();
            String[] fechaPartes = fechaBruta.split("/");

            if (fechaPartes.length == 3) {
                int year;

                try {
                    year = Integer.parseInt(fechaPartes[2]);
                } catch (NumberFormatException e) {
                    System.out.println("El año introducido no es un número valido!");
                    continue;
                }

                if (year < 1) {
                    System.out.println("El año introducido no puede ser menor que 0!");
                    continue;
                }

                int mes;

                try {
                    mes = Integer.parseInt(fechaPartes[1]);
                } catch (NumberFormatException e) {
                    System.out.println("El mes introducido no es un número valido!");
                    continue;
                }

                if (mes < 1 || mes > 12) {
                    System.out.println("El mes introducido no puede ser menor 1 o mayor que 12!");
                    continue;
                }

                int dia;

                try {
                    dia = Integer.parseInt(fechaPartes[0]);
                } catch (NumberFormatException e) {
                    System.out.println("El día introducido no es un número valido!");
                    continue;
                }

                int diasDelMes = diasDelMes(mes, year);
                if (dia < 0) {
                    System.out.println("El dia introducido no puede ser menor que 0!");
                    continue;
                } else if (dia > diasDelMes) {
                    System.out.println("El dia '" + dia + "' introducido es mayor que los dias del mes '" + diasDelMes + "'!");
                    continue;
                }

                return new int[] { dia, mes, year };
            } else {
                System.out.println("Formato de fecha incorrecto!");
            }
        }
    }

    private static int[] leerHora(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);

            String horaBruta = scanner.nextLine();
            String[] horaPartes = horaBruta.split(":");

            if (horaPartes.length == 3) {
                int hora;

                try {
                    hora = Integer.parseInt(horaPartes[0]);
                } catch (NumberFormatException e) {
                    System.out.println("La hora introducido no es un número valido!");
                    continue;
                }

                if (hora < 0 || hora > 23) {
                    System.out.println("La hora introducido no puede ser menor que 0 o mayor que 23!");
                    continue;
                }

                int minuto;

                try {
                    minuto = Integer.parseInt(horaPartes[1]);
                } catch (NumberFormatException e) {
                    System.out.println("El minuto introducido no es un número valido!");
                    continue;
                }

                if (minuto < 0 || minuto > 59) {
                    System.out.println("El minuto introducido no puede ser menor 0 o mayor que 59!");
                    continue;
                }

                int segundo;

                try {
                    segundo = Integer.parseInt(horaPartes[2]);
                } catch (NumberFormatException e) {
                    System.out.println("El segundo introducido no es un número valido!");
                    continue;
                }

                if (segundo < 0 || segundo > 59) {
                    System.out.println("El segundo introducido no puede ser menor 0 o mayor que 59!");
                    continue;
                }

                return new int[] { hora, minuto, segundo };
            } else {
                System.out.println("Formato de hora incorrecto!");
            }
        }
    }

    public static int leerInt(Scanner scanner, String message, int min, int max) {
        while (true) {
            System.out.println(message);

            try {
                int numero = Integer.parseInt(scanner.nextLine());

                if (min != -1 && numero < min) {
                    System.out.printf("El número no puede ser menor que %d!\n", min);
                } else if (max != -1 && numero > max) {
                    System.out.printf("El número no puede ser mayor que %d!\n", max);
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor introducido no es un número valido!");
            }
        }
    }

    /**
     * Lee un double del Scanner, si el valor introducido no es un double
     * vuelve a pedir el valor, una vez recibe un double valido
     * comprueba si este esta dentro de los rangos introducidos
     * @param scanner el scanner donde se lee el imput
     * @param message el mensaje que se envia cada vez que se lee
     * @param min el valor minimo (-1 para desactivarlo)
     * @param max el valor maximo (-1 para desactivarlo)
     * @return el double verificado
     */

    public static double leerDouble(Scanner scanner, String message, double min, double max) {
        while (true) {
            System.out.println(message);

            try {
                double numero = Double.parseDouble(scanner.nextLine());

                if (min != -1 && numero < min) {
                    System.out.printf("El número no puede ser menor que %.2f!\n", min);
                } else if (max != -1 && numero > max) {
                    System.out.printf("El número no puede ser mayor que %.2f!\n", max);
                } else {
                    return numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor introducido no es un número valido!");
            }
        }
    }
}