package me.matamor.pruebas.tema11.ejercicio5;

import me.matamor.pruebas.lib.Generador;
import me.matamor.pruebas.lib.Randomizer;

public class GeneradorItem implements Generador<Item> {

    private Material randomMaterial() {
        Material[] materiales = Material.values();
        return materiales[Randomizer.randomInt(0, materiales.length - 1)];
    }

    @Override
    public Item generar() {
        Material material = randomMaterial();
        int cantidad = Randomizer.randomInt(1, material.getStackSize());

        return new Item(material, cantidad);
    }
}
