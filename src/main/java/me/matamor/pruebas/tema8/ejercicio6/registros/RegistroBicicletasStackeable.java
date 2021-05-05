package me.matamor.pruebas.tema8.ejercicio6.registros;

import me.matamor.pruebas.tema8.ejercicio6.Bicicleta;
import me.matamor.pruebas.lib.Validate;

import java.util.Arrays;

public class RegistroBicicletasStackeable implements RegistroBicicleta {

    private static final int CAPACIDAD_INICIAL = 1000;
    private static final int INCREMENTO_CAPACIDAD = 1000;

    private Bicicleta[] bicicletas;
    private int contador;

    public RegistroBicicletasStackeable() {
        this.bicicletas = new Bicicleta[CAPACIDAD_INICIAL];
        this.contador = 0;
    }

    private void asegurarCapacidad() {
        if (this.contador >= this.bicicletas.length) {
            Bicicleta[] bicicletas = new Bicicleta[this.bicicletas.length + INCREMENTO_CAPACIDAD];
            for (int i = 0; this.bicicletas.length > i; i++) {
                bicicletas[i] = this.bicicletas[i];
            }

            this.bicicletas = bicicletas;
        }
    }

    @Override
    public void registrarBicicleta(Bicicleta bicicleta) {
        Validate.notNull(bicicleta, "La bicicleta no puede ser null!");

        asegurarCapacidad();

        this.bicicletas[this.contador++] = bicicleta;
    }

    @Override
    public void registrarBicicletas(Bicicleta... bicicletas) {
        Validate.notNull(bicicletas, "Las bicicletas no pueden ser null!");

        for (Bicicleta bicicleta : bicicletas) {
            registrarBicicleta(bicicleta);
        }
    }

    @Override
    public Bicicleta buscarBicicleta(int referencia) {
        for (int i = 0; this.contador > i; i++) {
            Bicicleta bicicleta = this.bicicletas[i];

            if (bicicleta.getReferencia() == referencia) {
                return bicicleta;
            }
        }

        return null;
    }

    @Override
    public Bicicleta[] buscarBicicletas(CondicionBusqueda condicionBusqueda) {
        Bicicleta[] bicicletas = new Bicicleta[this.contador];
        int contador = 0;

        for (int i = 0; this.contador > i; i++) {
            Bicicleta bicicleta = this.bicicletas[i];

            if (condicionBusqueda.valido(bicicleta)) {
                bicicletas[contador++] = bicicleta;
            }
        }

        return Arrays.copyOfRange(bicicletas, 0, contador);
    }

    @Override
    public Bicicleta[] bicicletas() {
        Bicicleta[] bicicletas = new Bicicleta[this.contador];
        for (int i = 0; this.contador > i; i++) {
            bicicletas[i] = this.bicicletas[i];
        }

        return Arrays.copyOfRange(this.bicicletas, 0, this.contador);
    }
}
