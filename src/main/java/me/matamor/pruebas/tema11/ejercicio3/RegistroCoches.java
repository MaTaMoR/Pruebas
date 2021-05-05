package me.matamor.pruebas.tema11.ejercicio3;

import java.util.HashMap;
import java.util.Map;

public class RegistroCoches {

    private final Map<String, Coche> coches = new HashMap<>();

    public void registrarCoche(Coche coche) throws CocheException {
        if (this.coches.containsKey(coche.getMatricula())) {
            throw new CocheException("Ya hay un coche registrado con esa matricula!");
        }

        this.coches.put(coche.getMatricula(), coche);
    }

    public Coche buscarCoche(String matricula) {
        return this.coches.get(matricula);
    }
}
