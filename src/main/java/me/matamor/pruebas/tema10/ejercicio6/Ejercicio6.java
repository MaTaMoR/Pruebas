package me.matamor.pruebas.tema10.ejercicio6;

import me.matamor.pruebas.lib.Input;

public class Ejercicio6 {

    private static final int INTRODUCIR_PALABRAS = 1;
    private static final int TRADUCIR_PALABRAS = 2;
    private static final int SALIR = 0;

    private static final int MIN_PARES = 1;
    private static final int MIN_PALABRA = 2;

    private final Traductor traductor;

    public Ejercicio6() {
        this.traductor = new Traductor();
    }

    private void introducirPares() {
        System.out.print("¿Cuántas palabras deseas introducir? ");

        int cantidad = Input.leerInt(MIN_PARES, -1);
        for (int i = 0; cantidad > i; i++) {
            System.out.print("Introduce palabra en inglés: ");
            String palabra = Input.leerLinea(MIN_PALABRA, -1);

            System.out.print("Introduce traducción al valenciano: ");
            String traduccion = Input.leerLinea(MIN_PALABRA, -1);

            this.traductor.guardarTraduccion(palabra, traduccion);
        }
    }

    private void traducir() {
        System.out.print("Palabra a buscar: ");
        String palabra = Input.leerLinea(MIN_PALABRA, -1);

        String traduccion = this.traductor.traducir(palabra);
        if (traduccion == null) {
            System.out.println("La palabra no existe.");
        } else {
            System.out.println(traduccion);
        }
    }

    private int mostrarMenu() {
        do {
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("==============");
            System.out.println(INTRODUCIR_PALABRAS + ". Introducir parejas de palabras.");
            System.out.println(TRADUCIR_PALABRAS + ". Traducir palabras.");
            System.out.println(SALIR + ". Salir de la aplicación.");

            System.out.print("\nEligue una opción: ");

            int opcion = Input.leerInt();

            switch (opcion) {
                case INTRODUCIR_PALABRAS:
                case TRADUCIR_PALABRAS:
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
                case SALIR: {
                    System.out.println("Saliendo de la aplicación...");
                    break;
                }
                case INTRODUCIR_PALABRAS: {
                    introducirPares();
                    Input.esperarEnter();
                    break;
                }
                case TRADUCIR_PALABRAS: {
                    traducir();
                    Input.esperarEnter();
                    break;
                }
            }
        } while (opcion != SALIR);
    }

    public static void main(String[] args) {
        Ejercicio6 ejercicio6 = new Ejercicio6();
        ejercicio6.execute();
    }
}
