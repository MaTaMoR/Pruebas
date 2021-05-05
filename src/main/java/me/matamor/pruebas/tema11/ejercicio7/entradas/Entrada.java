package me.matamor.pruebas.tema11.ejercicio7.entradas;

import me.matamor.pruebas.tema11.ejercicio7.estadio.RegistroEntradas;

public abstract class Entrada {

    private final RegistroEntradas registroEntradas;

    private final int identificador;

    private final int zona;
    private final int fila;
    private final int asiento;

    private final double precio;

    public Entrada(RegistroEntradas registroEntradas, int zona, int fila, int asiento, double precio) {
        this.registroEntradas = registroEntradas;

        this.identificador = EntradaUtil.crearId(zona, fila, asiento);

        this.zona = zona;
        this.fila = fila;
        this.asiento = asiento;

        this.precio = precio;
    }

    public RegistroEntradas getRegistroEntradas() {
        return this.registroEntradas;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public int getZona() {
        return this.zona;
    }

    public int getFila() {
        return this.fila;
    }

    public int getAsiento() {
        return this.asiento;
    }

    public double getPrecio() {
        return this.precio;
    }
}
