package me.matamor.pruebas.test;

import java.util.Random;
import java.util.Scanner;

public class PiedraPapelTijeras {

    private static final int NUEVA_PARTIDA = 1;
    private static final int MOSTRAR_PUNTUACIONES = 2;
    private static final int SALIR = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int puntuacionJugador = 0;
        int puntuacionMaquina = 0;
        int empates = 0;

        int opcion;

        do {
            mostrarMenu();
            opcion = preguntarNumero(scanner);

            if (opcion == SALIR) {
                System.out.println("Saliendo...");
            } else {
                if (opcion == NUEVA_PARTIDA) {
                    Resultado resultado = jugarRonda(scanner);
                    if (resultado == Resultado.VICTORIA) {
                        puntuacionJugador++;
                    } else if (resultado == Resultado.DERROTA) {
                        puntuacionMaquina++;
                    } else {
                        empates++;
                    }
                } else if (opcion == MOSTRAR_PUNTUACIONES) {
                    mostrarPuntuaciones(puntuacionJugador, puntuacionMaquina, empates);
                } else {
                    System.out.println("La opción introducida '" + opcion + "' no es valida!");
                }
                
                System.out.println("\nPulsa INTRO para continuar...");
                scanner.nextLine();

                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } while (opcion != SALIR);
    }

    /**
     * Esta ENUM se utiliza para los diferentes tipos de Manos de la partida
     */

    public enum Mano {

        PIEDRA(1),
        PAPEL(2),
        TIJERA(3);

        private final int posicion;

        Mano(int posicion) {
            this.posicion = posicion;
        }

        public int getPosicion() {
            return posicion;
        }

        public static Mano buscar(int valor) {
            for (Mano mano : values()) {
                if (mano.getPosicion() == valor) {
                    return mano;
                }
            }

            return null;
        }
    }

    /**
     * Esta ENUM se utiliza para el resultado de la partida
     */

    public enum Resultado {

        EMPATE,
        VICTORIA,
        DERROTA;

    }

    /**
     * Muestra el menu principal
     */

    public static void mostrarMenu() {
        System.out.println("===========================");
        System.out.println("  PIEDRA, PAPEL O TIJERAS");
        System.out.println("===========================");
        System.out.println(NUEVA_PARTIDA + ". Nueva Partida ...");
        System.out.println(MOSTRAR_PUNTUACIONES + ". Mostrar puntuaciones ...");
        System.out.println("---------------------------");
        System.out.println(SALIR + ". Salir");
    }

    /**
     * Muestra el menu de seleccion
     */

    public static void mostrarSeleccion() {
        System.out.println("************  ELIGE  ************");
        System.out.println(Mano.PIEDRA.getPosicion() + ". Piedra");
        System.out.println(Mano.PAPEL.getPosicion() + ". Papel");
        System.out.println(Mano.TIJERA.getPosicion() + ". Tijeras");
    }

    /**
     * Muestra la puntuacion formateada
     * @param puntuacionJugador la puntuacion del jugador
     * @param puntuacionMaquina la puntuacion de la maquina
     * @param empate los empates
     */

    public static void mostrarPuntuaciones(int puntuacionJugador, int puntuacionMaquina, int empate) {
        System.out.println("***** PUNTUACIONES ******");
        System.out.printf("%-20s %-10s\n", "Victorias Player:", puntuacionJugador);
        System.out.printf("%-20s %-10s\n", "Victorias CPU:", puntuacionMaquina);
        System.out.printf("%-20s %-10s\n", "Empates:", empate);
    }

    /**
     * Genera un numero aleatorio entre los rangos
     * @param min el valor minimo
     * @param max el valor maximo
     * @return el valora aleatorio generado
     */

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min + 1)) + min;
    }

    /**
     * Genera la mano para la maquina
     * @return devuelve la mano de la maquina
     */

    public static Mano generarMano() {
        Mano[] manos = Mano.values();
        int random = random(0, manos.length - 1);

        return manos[random];
    }

    /**
     * Comprueba quien gana la ronda
     * @param manoJugador la mano del jugador
     * @param manoMaquina la mano de la maquina
     * @return devuelve quien ha ganado
     */

    public static Resultado comprobar(Mano manoJugador, Mano manoMaquina) {
        if (manoJugador == manoMaquina) {
            return Resultado.EMPATE;
        } else if (manoJugador == Mano.PIEDRA) { //Piedra
            return (manoMaquina == Mano.TIJERA ? Resultado.VICTORIA : Resultado.DERROTA);
        } else if (manoJugador == Mano.PAPEL) { //Papel
            return (manoMaquina == Mano.PIEDRA ? Resultado.VICTORIA : Resultado.DERROTA);
        } else { //Tijeras
            return (manoMaquina == Mano.PAPEL ? Resultado.VICTORIA : Resultado.DERROTA);
        }
    }

    /**
     * Devuelve la descripcion de la mano
     * @param manoJugador la mano del jugador
     * @param manoMaquina la mano de la maquina
     * @return la descripcion de la jugada
     */

    public static String descripcion(Mano manoJugador, Mano manoMaquina) {
        if (manoJugador == manoMaquina) {
            return "Has empatado!";
        } else if (manoJugador == Mano.PIEDRA) { //Piedra
            if (manoMaquina == Mano.TIJERA) {
                return "La PIEDRA rompe las TIJERAS!";
            } else {
                return "La PIEDRA no rompe el PAPEL!";
            }
        } else if (manoJugador == Mano.PAPEL) { //Papel
            if (manoMaquina == Mano.PIEDRA) {
                return "El PAPEL envuelve la PIEDRA!";
            } else {
                return "El PAPEL no envuelve el PAPEL!";
            }
        } else { //Tijera
            if (manoMaquina == Mano.PAPEL) {
                return "Las TIJERAS cortan el PAPEL!";
            } else {
                return "Las TIJERAS no cortan la PIEDRA!";
            }
        }
    }

    /**
     * Pregunta por un numero hasta que el usuario introduzca un numero valido
     * @return el numero
     */

    public static int preguntarNumero(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("El valor introducido no es un número valido!");
            }
        } while (true);
    }

    /**
     * Pregunta al jugador por su mano hasta que introduzca una valida
     * @return la mano del jugador
     */

    public static Mano preguntarJugador(Scanner scanner) {
        do {
            mostrarSeleccion();

            int numero = preguntarNumero(scanner);
            Mano mano = Mano.buscar(numero);

            if (mano == null) {
                System.out.println("La mano introducida no existe!");
            } else {
                return mano;
            }
        } while (true);
    }

    /**
     * Juega una ronda y devuelve el ganador
     * @return el ganador
     */

    public static Resultado jugarRonda(Scanner scanner) {
        Mano manoJugador = preguntarJugador(scanner);
        System.out.println("Player elige " + manoJugador.name());
        
        Mano manoMaquina = generarMano();
        System.out.println("CPU elige " + manoMaquina.name());

        //Linea en blanco
        System.out.println();

        //Seria mejor implementar un metodo abstracto en la enum, pero esto no lo hemos dado en clase
        Resultado resultado = comprobar(manoJugador, manoMaquina);
        if (resultado == Resultado.VICTORIA) {
            System.out.println("Has ganado!!");
        } else {
            System.out.println("Has perdido!!");
        }

        //Mostramos la descripcion de la ronda
        System.out.println(descripcion(manoJugador, manoMaquina));

        return resultado;
    }
}
