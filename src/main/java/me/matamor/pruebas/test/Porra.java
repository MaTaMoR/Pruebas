package me.matamor.pruebas.test;

import java.util.Random;
import java.util.Scanner;

public class Porra {

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.mostrarDescripcion(); //Mostramos la descripción

        //El juego se ejecutara mientras no haya ganador
        while (!juego.hayGanador()) {
            //Realizamos el turno del jugador
            Juego.ResultadoTurno turnoJugador = juego.turnoJugador();

            //Si el jugador se retira debemos ejecutar el turno de la maquina
            if (turnoJugador == Juego.ResultadoTurno.CONTINUAR) {
                juego.turnoMaquina();
            }

            //Reiniciamos la puntuacion antes del siguiente turno
            juego.reiniciarPuntuacion();

            //Creamos una espera antes del siguiente turno para que no se tan rapido
            juego.esperar();
        }

        //Mostramos el ganador del juego
        juego.mostrarGanador();
    }

    public static class Juego {

        public enum ResultadoTurno {

            CONTINUAR,
            VICTORIA,
            VICTORIA_PERFECTA,
            DERROTA

        }

        private final int DADO_MINIMO = 1;
        private final int DADO_MAXIMO = 6;

        private final int PUNTOS_MAXIMOS = 11; //El maximo de puntos
        private final int PORRAS_MAXIMAS = 5; //El maximo de porras

        private final int PORRAS_POR_VICTORIA = 1; //Las porras que se ganan en una victoria normal
        private final int PORRAS_POR_VICTORIA_PERFECTA = 2; //Las porras que se ganan en una victoria perfecta

        private static final int ESPERA_ENTRE_TURNOS = 2500; //Tiempo de spera

        private int puntuacionJugador = 0;
        private int puntuacionMaquina = 0;

        private int porrasJugador = 0;
        private int porrasMaquina = 0;

        private final Scanner scanner;
        private final Random random;

        public Juego() {
            this.scanner = new Scanner(System.in);
            this.random = new Random();
        }

        public void ejecutar() {
            //No uso este metodo porque el profe dijo que no quería el main vacio xD
            reiniciarPuntuacion(); //Reiniciamos la puntuacion
            reiniciarPorras(); //Reiniciamos las porras

            //El juego se ejecutara mientras no haya ganador
            while (!hayGanador()) {
                //Realizamos el turno del jugador
                ResultadoTurno turnoJugador = turnoJugador();

                //Si el jugador se retira debemos ejecutar el turno de la maquina
                if (turnoJugador == ResultadoTurno.CONTINUAR) {
                    turnoMaquina();
                }

                //Reiniciamos la puntuacion antes del siguiente turno
                reiniciarPuntuacion();
            }

            //Mostramos el ganador
            mostrarGanador();
        }

        /**
         * Juego el turno del jugador y devuelve un resultado [VICTORIA_PERFECTA, DERROTA, CONTINUAR]
         * @return el resultado del turno del jugador
         */

        public ResultadoTurno turnoJugador() {
            while (true) {
                int dado = tirarDado(); //Tiramos el dado
                puntuacionJugador += dado; //Sumamos el dado al total de puntos del jugador

                //Mostramos la información del dado que hemos tirado
                print("Tiras el dado y sacas un: " + dado, "Total de puntos: " + puntuacionJugador);

                if (puntuacionJugador == PUNTOS_MAXIMOS) {
                    //El jugador ha sacado 11 puntos, por lo tanto gana de manera perfecta!

                    porrasJugador += PORRAS_POR_VICTORIA_PERFECTA; //Sumamos las porras de vitoria perfecta
                    puntuacionJugador = 0; //Reiniciamos los puntos del jugador para el siguiente turno

                    //Mostramos por pantalla el mensaje de victoria y el total de porras
                    print("Tienes " + PUNTOS_MAXIMOS + " puntos... Ganas el turno!", "Porras del jugador: " + porrasJugador + " (+" + PORRAS_POR_VICTORIA_PERFECTA + ")");

                    return ResultadoTurno.VICTORIA_PERFECTA;
                } else if (puntuacionJugador > PUNTOS_MAXIMOS) {
                    //El jugador ha sacado más de 11 puntos, por lo tanto pierde!

                    porrasMaquina += PORRAS_POR_VICTORIA; //Sumamos a la maquina las porras por victoria
                    puntuacionJugador = 0; //Reiniciamos los puntos del jugador para el siguiente turno

                    //Mostramos por pantalla el mensaje de victoria y el total de porras
                    print("Tienes más de " + PUNTOS_MAXIMOS + " puntos... Pierdes el turno!", "Porras de la máquina: " + porrasMaquina + " (+" + PORRAS_POR_VICTORIA + ")");

                    return ResultadoTurno.DERROTA;
                } else {
                    //El jugador ha sacado menos de 11 puntos, por lo tanto puede seguir jugando o retirarse!

                    if (!seguirJugando()) {
                        print("Te retiras con " + puntuacionJugador + " puntos...", "Empieza el turno de la máquina");

                        return ResultadoTurno.CONTINUAR;
                    }
                }
            }
        }

        /**
         * Juego el turno de la maquina y devuelve un resultado [VICTORIA_PERFECTA, VICTORIA, DERROTA]
         * @return el resultado del turno de la maquina
         */

        public ResultadoTurno turnoMaquina() {
            while (true) {
                int dado = tirarDado(); //Tiramos los dados para la máquina
                puntuacionMaquina += dado; //Sumamos los puntos a la maquina

                print("La maquina tira el dado y saca un: " + dado, "Total de puntos: " + puntuacionMaquina);

                if (puntuacionMaquina == PUNTOS_MAXIMOS) {
                    //La máquina ha conseguido una puntuación perfecta, gana más puntos!
                    porrasMaquina += PORRAS_POR_VICTORIA_PERFECTA;

                    print("La máquina tiene " + PUNTOS_MAXIMOS + " puntos... La máquina gana la ronda!", "Porras de la maquina: " + porrasMaquina + " (+" + PORRAS_POR_VICTORIA_PERFECTA + ")");

                    return ResultadoTurno.VICTORIA_PERFECTA;
                } else {
                    if (puntuacionMaquina > PUNTOS_MAXIMOS) {
                        //La maquina ha sacado mas de 11 puntos, por lo cual pierde!
                        porrasJugador += PORRAS_POR_VICTORIA;

                        print("La máquina ha sacado más de " + PUNTOS_MAXIMOS + " puntos... La máquina pierde la ronda!", "Porras del jugador: " + porrasJugador + " (+" + PORRAS_POR_VICTORIA + ")");

                        return ResultadoTurno.DERROTA;
                    } else if (puntuacionMaquina >= puntuacionJugador) {
                        //La maquina ha sacado mas o ha igualado los puntos del jugador, gana la ronda!
                        porrasMaquina += PORRAS_POR_VICTORIA;

                        print("La máquina ha igualado o superado los puntos del jugador... La máquina gana la ronda!", "Porras de la máquina: " + porrasMaquina + " (+" + PORRAS_POR_VICTORIA + ")");

                        return ResultadoTurno.VICTORIA;
                    }
                }

                //Si el juego no acaba en un turno esperamos antes del siguiente
                esperar();
            }
        }

        /**
         * Mostramos la descripción del juego
         */
        private void mostrarDescripcion() {
            print(
                    "El juego consiste en acumular " + PORRAS_MAXIMAS + " porras para ganar!",
                    "Ganas " + PORRAS_POR_VICTORIA_PERFECTA + " porras si consigues " + PUNTOS_MAXIMOS + " puntos",
                    "Si consigues más de " + PUNTOS_MAXIMOS + " puntos pierdes el turno y gana " + PORRAS_POR_VICTORIA + " la máquina",
                    "Si te retiras antes de conseguir " + PUNTOS_MAXIMOS + " empieza el turno de la máquina",
                    "",
                    "Pulsa ENTER para empezar"
            );

            scanner.nextLine();
        }

        /**
         * Reinicia la puntuacion del juego
         */

        private void reiniciarPuntuacion() {
            this.puntuacionJugador = 0;
            this.puntuacionMaquina = 0;
        }

        /**
         * Reinicia las porras del juego
         */

        private void reiniciarPorras() {
            this.porrasJugador = 0;
            this.porrasMaquina = 0;
        }

        /**
         * Comprueba si el juego tiene un ganador
         * @return true si hay un ganador
         */

        private boolean hayGanador() {
            return porrasJugador >= PORRAS_MAXIMAS || porrasMaquina >= PORRAS_MAXIMAS;
        }

        /**
         * Tira los dados dentro del minimo y maximo permitido y devuelve el valor
         * @return el valor del dado
         */

        private int tirarDado() {
            return random.nextInt(DADO_MAXIMO - DADO_MINIMO - 1) + DADO_MINIMO;
        }

        private void mostrarGanador() {
            if (porrasJugador >= PORRAS_MAXIMAS) {
                print("El jugador ha ganado con " + porrasJugador + " porras!");
            } else if (porrasMaquina >= PORRAS_MAXIMAS) {
                print("La maquina ha ganado con " + porrasMaquina + " porras!");
            }
        }

        /**
         * Le pregunta al jugador si quiere seguir jugando hasta que de una respuesta valida
         * @return true si el jugador quiere seguir jugando
         */

        private boolean seguirJugando() {
            while (true) {
                print("¿ Deseas seguir tirando dados ? (si/no)");

                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("si")) {
                    return true;
                } else if (respuesta.equals("no")) {
                    return false;
                } else {
                    print("Respuesta introducida invalida!");
                }
            }
        }

        /**
         * Muestra el mensaje por pantalla centrado, además se añade un header y footer
         * @param strings el mensaje que se mostrara
         */

        private void print(String... strings) {
            int maxLength = 60;

            for (String string : strings) {
                int length = string.length();

                if (length > maxLength) {
                    maxLength = length;
                }
            }

            String lines = "-".repeat(maxLength);
            System.out.printf("%s\n\n", lines);

            for (String string : strings) {
                int length = string.length();
                int spaces = (maxLength - length) / 2;

                System.out.println(" ".repeat(spaces) + string);
            }

            System.out.printf("\n%s\n", lines);
        }

        /**
         * Hace que el juego se espere antes del siguiente turno
         */

        private void esperar() {
            try {
                Thread.sleep(ESPERA_ENTRE_TURNOS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}