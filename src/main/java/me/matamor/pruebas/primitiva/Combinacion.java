package me.matamor.pruebas.primitiva;

import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private final List<Integer> numeros;
    private final int complementario;
    private final int reintegro;

    public Combinacion(List<Integer> numeros, int complementario, int reintegro) {
        this.numeros = numeros;
        this.complementario = complementario;
        this.reintegro = reintegro;
    }

    public List<Integer> getNumeros() {
        return this.numeros;
    }

    public int getComplementario() {
        return this.complementario;
    }

    public int getReintegro() {
        return this.reintegro;
    }

    public Premio comparar(Combinacion combinacion) {
        if (combinacion == null) {
            return null;
        }

        int condicencias = 0;

        for (Integer numero : combinacion.getNumeros()) {
            if (this.numeros.contains(numero)) {
                condicencias++;
            }
        }

        boolean complementario = this.complementario == combinacion.getComplementario();
        boolean reintegro = this.reintegro == combinacion.getReintegro();

        switch (condicencias) {
            case 6: {
                return (reintegro ? Premio.ESPECIAL : Premio.PRIMERO);
            }
            case 5: {
                return (complementario ? Premio.SEGUNDO : Premio.TERCERO);
            }
            case 4: {
                return Premio.CUARTO;
            }
            case 3: {
                return Premio.QUINTO;
            }
            default:
                return (reintegro ? Premio.REINTEGRO : null);
        }
    }

    @Override
    public String toString() {
        return String.format("Numeros: %s Complementario: %d Reintegro: %d", this.numeros, this.complementario, this.reintegro);
    }
}
