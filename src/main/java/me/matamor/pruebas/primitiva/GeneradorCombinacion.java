package me.matamor.pruebas.primitiva;

import me.matamor.pruebas.lib.Generador;
import me.matamor.pruebas.lib.Ints;
import me.matamor.pruebas.lib.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class GeneradorCombinacion implements Generador<Combinacion> {

    private final Bombo primerBombo;

    public GeneradorCombinacion() {
        this.primerBombo = new Bombo(Ints.generarInts(Constantes.PRIMER_BOMBO_MIN, Constantes.PRIMER_BOMBO_MAX));
    }

    private void reiniciar() {
        this.primerBombo.reiniciar();
    }

    @Override
    public Combinacion generar() {
        reiniciar();

        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; 6 > i; i++) {
            numeros.add(this.primerBombo.aleatorio());
        }

        int complementario = this.primerBombo.aleatorio();
        int reintegro = Randomizer.randomInt(Constantes.SEGUNDO_BOMBO_MIN, Constantes.SEGUNDO_BOMBO_MAX);

        return new Combinacion(numeros, complementario, reintegro);
    }
}
