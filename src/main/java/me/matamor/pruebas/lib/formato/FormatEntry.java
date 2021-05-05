package me.matamor.pruebas.lib.formato;

public interface FormatEntry<T> {

    String getNombre();

    String getValor(T objeto);

}
