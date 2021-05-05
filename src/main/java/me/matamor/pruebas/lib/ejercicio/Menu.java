package me.matamor.pruebas.lib.ejercicio;

public interface Menu {

    String nombre();

    void registrarOpcion(int posicion, Opcion opcion) throws EjercicioException;

    boolean tieneOpcion(int posicion);

    Opcion borrarOpcion(int posicion);

    Opcion mostrarMenu() throws EjercicioException;

    void ejecutar() throws EjercicioException;

}
