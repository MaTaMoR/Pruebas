package me.matamor.pruebas.lib.formato;

public abstract class SimpleFormatEntry<T> implements FormatEntry<T> {

    private final String nombre;

    public SimpleFormatEntry(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
