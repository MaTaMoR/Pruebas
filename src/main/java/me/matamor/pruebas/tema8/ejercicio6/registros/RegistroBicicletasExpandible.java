package me.matamor.pruebas.tema8.ejercicio6.registros;

import me.matamor.pruebas.tema8.ejercicio6.Bicicleta;
import me.matamor.pruebas.lib.Validate;

import java.util.Arrays;

public class RegistroBicicletasExpandible implements RegistroBicicleta {

    private static final int CAPACIDAD_INICIAL = 1000;
    private static final int INCREMENTO_CAPACIDAD = 1000;

    private Bicicleta[] bicicletas;
    private int contador;

    public RegistroBicicletasExpandible() {
        this.bicicletas = new Bicicleta[CAPACIDAD_INICIAL];
        this.contador = 0;
    }

    private void asegurarCapacidad(int size) {
        if (size >= this.bicicletas.length) {
            Bicicleta[] bicicletas = new Bicicleta[size + INCREMENTO_CAPACIDAD];
            for (int i = 0; this.bicicletas.length > i; i++) {
                bicicletas[i] = this.bicicletas[i];
            }

            this.bicicletas = bicicletas;
        }
    }

    @Override
    public void registrarBicicleta(Bicicleta bicicleta) {
        Validate.notNull(bicicleta, "La bicicleta no puede ser null!");

        asegurarCapacidad(bicicleta.getReferencia());

        Bicicleta registro = this.bicicletas[bicicleta.getReferencia()];
        Validate.isNull(registro, "Ya hay una bicicleta registrada con la referencia: " + bicicleta.getReferencia());

        this.bicicletas[bicicleta.getReferencia()] = bicicleta;
        this.contador++;
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
        if (referencia < 0 || referencia >= this.bicicletas.length) {
            return null;
        }

        return this.bicicletas[referencia];
    }

    @Override
    public Bicicleta[] buscarBicicletas(CondicionBusqueda condicionBusqueda) {
        Bicicleta[] bicicletas = new Bicicleta[this.contador];
        int contador = 0;

        for (Bicicleta bicicleta : this.bicicletas) {
            if (condicionBusqueda.valido(bicicleta)) {
                bicicletas[contador++] = bicicleta;
            }
        }

        return Arrays.copyOfRange(bicicletas, 0, contador);
    }

    @Override
    public Bicicleta[] bicicletas() {
        Bicicleta[] bicicletas = new Bicicleta[this.contador];
        int contador = 0;

        for (Bicicleta bicicleta : this.bicicletas) {
            if (bicicleta != null) {
                bicicletas[contador++] = bicicleta;
            }
        }

        return bicicletas;
    }
}
