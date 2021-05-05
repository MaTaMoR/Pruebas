package me.matamor.pruebas.tema10.ejercicio9;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ejercicio9 {

    private final int NUEVA_PALABRA = 1;
    private final int MODIFICAR_PALABRA = 2;
    private final int ELIMINAR_PALABRA = 3;
    private final int CONSULTAR_PALABRA = 4;
    private final int MOSTRAR_DICCIONARIO = 5;
    private final int JUGAR = 6;
    private final int MEJORES_PUNTUACIONES = 7;
    private final int SALIR = 0;

    private final int MIN_PALABRA = 2;
    private final int MIN_DEFINICION = 3;
    private final int MIN_NOMBRE = 2;

    private final int CANTIDAD_TOP = 5;

    private final Diccionario diccionario;
    private final Puntuaciones puntuaciones;

    public Ejercicio9() {
        this.diccionario = new Diccionario();
        this.diccionario.registrarDefinicion("hola", "Es el saludo más común!");
        this.diccionario.registrarDefinicion("coche", "Es un vehiculo terrestre con 4 ruedas!");
        this.diccionario.registrarDefinicion("helicoptero", "Es un vehiculo aereo con 2 helices!");

        this.puntuaciones = new Puntuaciones();
    }

    private void nuevaPalabra() {
        System.out.print("Introduce la nueva palabra: ");
        String palabra = Input.leerLinea(MIN_PALABRA, -1);

        if (this.diccionario.existeDefinicion(palabra)) {
            System.out.printf("Ya existe una definición para la palabra: %s!\n", palabra);
        } else {
            System.out.print("Introduce la definición: ");
            String definicion = Input.leerLinea(MIN_DEFINICION, -1);

            this.diccionario.registrarDefinicion(palabra, definicion);
            System.out.printf("Palabra registrada!\nPalabra: %s / Definición: %s\n", palabra, definicion);
        }
    }

    private void modificarPalabra() {
        System.out.print("Introduce la nueva palabra: ");
        String palabra = Input.leerLinea(MIN_PALABRA, -1);

        if (this.diccionario.existeDefinicion(palabra)) {
            System.out.print("Introduce la definición: ");
            String definicion = Input.leerLinea(MIN_DEFINICION, -1);

            this.diccionario.registrarDefinicion(palabra, definicion);
            System.out.printf("Palabra actualizada!\nPalabra: %s / Definición: %s\n", palabra, definicion);
        } else {
            System.out.printf("No existe una definición para la palabra: %s!\n", palabra);
        }
    }

    private void eliminarPalabra() {
        System.out.print("Introduce la nueva palabra: ");
        String palabra = Input.leerLinea(MIN_PALABRA, -1);

        if (this.diccionario.existeDefinicion(palabra)) {
            this.diccionario.existeDefinicion(palabra);
            System.out.println("Palabra eliminada!");
        } else {
            System.out.printf("No existe una definición para la palabra: %s!\n", palabra);
        }
    }

    private void consultarPalabra() {
        System.out.print("Introduce la nueva palabra: ");
        String palabra = Input.leerLinea(MIN_PALABRA, -1);

        String definicion = this.diccionario.buscarDefinicion(palabra);
        if (definicion == null) {
            System.out.printf("No existe una definición para la palabra: %s!\n", palabra);
        } else {
            System.out.printf("Palabra: %s / Definición: %s\n", palabra, definicion);
        }
    }

    private void mostrarDiccionario() {
        Map<String, String> definiciones = this.diccionario.definiciones();
        if (definiciones.isEmpty()) {
            System.out.println("No hay ningúna palabra registrada en el diccionario!");
        } else {
            System.out.println("Definiciones:");

            for (Map.Entry<String, String> entry : definiciones.entrySet()) {
                System.out.printf("Palabra: %s / Definición: %s\n", entry.getKey(), entry.getValue());
            }
        }
    }

    private void jugar() {
        Map<String, String> definiciones = this.diccionario.definiciones();

        if (definiciones.isEmpty()) {
            System.out.println("No hay ningúna palabra registrada en el diccionario!");
        } else {
            List<Map.Entry<String, String>> entries = new ArrayList<>(definiciones.entrySet());
            boolean activo;
            int puntuacion = 0;

            do {
                Map.Entry<String, String> entry = entries.get(Randomizer.randomInt(0, entries.size() - 1));

                System.out.println("Adivina la palabra con la siguiente definición:\n" + entry.getValue());
                String input = Input.leerLinea(MIN_PALABRA, -1);

                activo = input.equalsIgnoreCase(entry.getKey());

                if (activo) {
                    System.out.println("Correcto! Has adivinado la palabra detras de la definición!\n");
                    puntuacion++;
                } else {
                    System.out.println("Incorrecto! No has logrado adivinar la detras de la definición!\nTu puntuación: " + puntuacion + "\n");

                    if (this.puntuaciones.esTop(puntuacion, CANTIDAD_TOP)) {
                        System.out.println("Tu puntuación está dentro del top " + CANTIDAD_TOP + "!");
                        System.out.print("Introduce tu nombre para registrar tu puntuación: ");

                        String nombre = Input.leerLinea(MIN_NOMBRE, -1);
                        this.puntuaciones.registrarPuntuacion(nombre, puntuacion);

                        System.out.println("Tu puntuación ha sido registrada!");
                    } else {
                        System.out.println("Tu puntuación no está dentro del top " + puntuacion + "!");
                    }
                }
            } while (activo);
        }
    }

    private void mejoresPuntuaciones() {
        List<Map.Entry<String, Integer>> topPuntuaciones = this.puntuaciones.mejoresPuntuaciones(CANTIDAD_TOP);
        if (topPuntuaciones.isEmpty()) {
            System.out.println("No hay puntuaciones registradas!");
        } else {
            int posicion = 1;

            for (Map.Entry<String, Integer> entry : topPuntuaciones) {
                System.out.printf("%d - %s:%s", posicion++, entry.getKey(), entry.getValue());
            }
        }
    }

    private int mostrarMenu() {
        int opcion;

        do {
            System.out.println("***********************");
            System.out.println("* GESTIÓN DICCIONARIO *");
            System.out.println("***********************");
            System.out.println(NUEVA_PALABRA + ". Añadir palabra");
            System.out.println(MODIFICAR_PALABRA + ". Modificar palabra");
            System.out.println(ELIMINAR_PALABRA + ". Eliminar palabra");
            System.out.println(CONSULTAR_PALABRA + ". Consultar palabra");
            System.out.println(MOSTRAR_DICCIONARIO + ". Mostrar diccionario");
            System.out.println(JUGAR + ". Jugar");
            System.out.println(MEJORES_PUNTUACIONES + ". Mejores puntuaciones");
            System.out.println("---------------------");
            System.out.println(SALIR + ". Salir");

            opcion = Input.leerInt();

            switch (opcion) {
                case NUEVA_PALABRA:
                case MODIFICAR_PALABRA:
                case ELIMINAR_PALABRA:
                case CONSULTAR_PALABRA:
                case MOSTRAR_DICCIONARIO:
                case JUGAR:
                case MEJORES_PUNTUACIONES:
                case SALIR:
                    return opcion;
                default: {
                    System.out.println("La opción introducida no es valida!");
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
                case NUEVA_PALABRA: {
                    nuevaPalabra();
                    break;
                }
                case MODIFICAR_PALABRA: {
                    modificarPalabra();
                    break;
                }
                case ELIMINAR_PALABRA: {
                    eliminarPalabra();
                    break;
                }
                case CONSULTAR_PALABRA: {
                    consultarPalabra();
                    break;
                }
                case MOSTRAR_DICCIONARIO: {
                    mostrarDiccionario();
                    break;
                }
                case JUGAR: {
                    jugar();
                    break;
                }
                case MEJORES_PUNTUACIONES: {
                    mejoresPuntuaciones();
                    break;
                }
                case SALIR: {
                    System.out.println("Saliendo...");
                    break;
                }
            }

            if (opcion != SALIR) {
                Input.esperarEnter();
            }
        } while (opcion != SALIR);
    }

    public static void main(String[] args) {
        Ejercicio9 ejercicio9 = new Ejercicio9();
        ejercicio9.execute();
    }
}
