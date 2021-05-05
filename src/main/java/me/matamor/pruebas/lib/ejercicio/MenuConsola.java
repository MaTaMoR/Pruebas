package me.matamor.pruebas.lib.ejercicio;

import me.matamor.pruebas.lib.Input;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuConsola implements Menu {

    private static final int SALIR = 0;

    private final String nombre;
    private final Map<Integer, Opcion> opciones;

    public MenuConsola(String nombre) {
        this.nombre = nombre;
        this.opciones = new LinkedHashMap<>();
    }

    @Override
    public String nombre() {
        return this.nombre;
    }

    @Override
    public void registrarOpcion(int posicion, Opcion opcion) throws EjercicioException {
        if (posicion == SALIR) {
            throw new EjercicioException("La posicion de la opcion no puede ser la de salida!");
        } else if (this.opciones.containsKey(posicion)) {
            throw new EjercicioException("La posicion ya está registrada!");
        } else {
            this.opciones.put(posicion, opcion);
        }
    }

    @Override
    public boolean tieneOpcion(int posicion) {
        return this.opciones.containsKey(posicion);
    }

    @Override
    public Opcion borrarOpcion(int posicion) {
        return this.opciones.remove(posicion);
    }

    @Override
    public Opcion mostrarMenu() throws EjercicioException {
        if (this.opciones.isEmpty()){
            throw new EjercicioException("No hay ninguna opción registrada!");
        }

        int posicion;

        do {
            System.out.println("-----------------------");
            System.out.println(this.nombre);
            System.out.println("-----------------------");

            for (Map.Entry<Integer, Opcion> opcionEntry : this.opciones.entrySet()) {
                Opcion opcion = opcionEntry.getValue();

                System.out.printf("%d - %s: %s\n", opcionEntry.getKey(), opcion.nombre(), opcion.descripcion());
            }

            System.out.println("-----------------------");
            System.out.println(SALIR + " - Salir");

            posicion = Input.leerInt();

            if (posicion == SALIR) {
                return null;
            } else if (this.opciones.containsKey(posicion)) {
                return this.opciones.get(posicion);
            } else {
                System.out.printf("La opción introducida '%d' no existe!\n", posicion);
            }
        } while (true);
    }

    @Override
    public void ejecutar() throws EjercicioException {
        Opcion opcion;

        do {
            opcion = mostrarMenu();

            if (opcion == null) {
                System.out.println("Saliendo...");
            } else {
                opcion.ejecutar();
                Input.esperarEnter();
            }
        } while (opcion != null);
    }
}
