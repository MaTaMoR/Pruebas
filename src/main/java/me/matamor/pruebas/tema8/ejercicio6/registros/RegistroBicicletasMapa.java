package me.matamor.pruebas.tema8.ejercicio6.registros;

import me.matamor.pruebas.tema8.ejercicio6.Bicicleta;
import me.matamor.pruebas.lib.Validate;

import java.util.HashMap;
import java.util.Map;

public class RegistroBicicletasMapa implements RegistroBicicleta {

    private final Map<Integer, Bicicleta> bicicletas = new HashMap<>();

    @Override
    public void registrarBicicleta(Bicicleta bicicleta) {
        Validate.notNull(bicicleta, "La bicicleta no puede ser null!");
        Validate.isNull(buscarBicicleta(bicicleta.getReferencia()), "Ya hay una bicicleta registrada con esa referencia!");

        this.bicicletas.put(bicicleta.getReferencia(), bicicleta);
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
        return this.bicicletas.get(referencia);
    }

    @Override
    public Bicicleta[] buscarBicicletas(CondicionBusqueda condicionBusqueda) {
        return this.bicicletas.values().stream()
                .filter(condicionBusqueda::valido)
                .toArray(Bicicleta[]::new);
    }

    @Override
    public Bicicleta[] bicicletas() {
        return this.bicicletas.values().toArray(new Bicicleta[0]);
    }
}
