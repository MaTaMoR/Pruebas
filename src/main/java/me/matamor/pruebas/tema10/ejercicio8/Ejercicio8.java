package me.matamor.pruebas.tema10.ejercicio8;

import me.matamor.pruebas.lib.Input;

import java.util.Map;

public class Ejercicio8 {

    private final int NUEVA_PALABRA = 1;
    private final int MODIFICAR_PALABRA = 2;
    private final int ELIMINAR_PALABRA = 3;
    private final int CONSULTAR_PALABRA = 4;
    private final int MOSTRAR_DICCIONARIO = 5;
    private final int SALIR = 0;

    private final int MIN_PALABRA = 2;
    private final int MIN_DEFINICION = 3;

    private final Diccionario diccionario;

    public Ejercicio8() {
        this.diccionario = new Diccionario();
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
            System.out.println("---------------------");
            System.out.println(SALIR + ". Salir");

            opcion = Input.leerInt();

            switch (opcion) {
                case NUEVA_PALABRA:
                case MODIFICAR_PALABRA:
                case ELIMINAR_PALABRA:
                case CONSULTAR_PALABRA:
                case MOSTRAR_DICCIONARIO:
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
        Ejercicio8 ejercicio8 = new Ejercicio8();
        ejercicio8.execute();
    }
}
