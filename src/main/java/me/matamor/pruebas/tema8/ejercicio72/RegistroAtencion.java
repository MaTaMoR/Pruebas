package me.matamor.pruebas.tema8.ejercicio72;

import java.util.Arrays;

public class RegistroAtencion {

    private static final int CAPACIDAD_INICIAL = 5;
    private static final int INCREMENTO_CAPACIDAD = 5;

    private Atencion[] atenciones;
    private int contador;

    public RegistroAtencion() {
        this.atenciones = new Atencion[CAPACIDAD_INICIAL];
        this.contador = 0;
    }

    public int getContador() {
        return contador;
    }

    private void asegurarCapacidad() {
        if (this.contador >= this.atenciones.length) {
            Atencion[] atenciones = new Atencion[this.atenciones.length + INCREMENTO_CAPACIDAD];
            System.arraycopy(this.atenciones, 0, atenciones, 0, this.contador);

            this.atenciones = atenciones;
        }
    }

    public void registrarAtencion(Atencion atencion) {
        asegurarCapacidad();

        this.atenciones[this.contador++] = atencion;
    }

    public void registrarAtenciones(Atencion... atenciones) {
        for (Atencion atencion : atenciones) {
            registrarAtencion(atencion);
        }
    }

    public Atencion ultimaAtencion() {
        return this.atenciones[this.contador];
    }

    public Atencion[] atenciones() {
        return Arrays.copyOfRange(this.atenciones, 0, this.contador);
    }
}
