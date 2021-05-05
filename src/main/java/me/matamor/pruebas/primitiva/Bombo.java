package me.matamor.pruebas.primitiva;

import me.matamor.pruebas.lib.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bombo {

    private final List<Integer> numeros;
    private List<Integer> bombo;

    public Bombo(int[] numeros) {
        this.numeros = Arrays.stream(numeros).boxed().collect(Collectors.toList());
        this.bombo = new ArrayList<>(this.numeros);
    }

    public Bombo(List<Integer> numeros) {
        this.numeros = numeros;
        this.bombo = new ArrayList<>(numeros);
    }

    public int aleatorio() throws BomboException {
        if (this.bombo.isEmpty()) {
            throw new BomboException("No hay ningún numero en el bombo!");
        }

        int posicion = Randomizer.randomInt(0, this.bombo.size() - 1);
        return this.bombo.remove(posicion);
    }

    public void reiniciar() {
        this.bombo = new ArrayList<>(this.numeros);
    }
}
