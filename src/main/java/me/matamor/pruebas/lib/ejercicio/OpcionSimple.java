package me.matamor.pruebas.lib.ejercicio;

public class OpcionSimple implements Opcion {

    private final String nombre;
    private final String descripcion;
    private final Runnable runnable;

    public OpcionSimple(String nombre, String descripcion, Runnable runnable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.runnable = runnable;
    }

    @Override
    public String nombre() {
        return this.nombre;
    }

    @Override
    public String descripcion() {
        return this.descripcion;
    }

    @Override
    public void ejecutar() {
        this.runnable.run();
    }
}
