package me.matamor.pruebas.tema11.ejercicio7.entradas;

import me.matamor.pruebas.lib.Randomizer;
import me.matamor.pruebas.tema11.ejercicio7.datos.Constantes;
import me.matamor.pruebas.tema11.ejercicio7.estadio.RegistroEntradas;

public class EntradaVIP extends Entrada {

    private final String password;

    public EntradaVIP(RegistroEntradas registroEntradas, int zona, int fila, int asiento, double precio) {
        super(registroEntradas, zona, fila, asiento, precio);

        this.password = getIdentificador() + "-" + Randomizer.randomInt(0, Constantes.PASSWORD_MAX);
    }

    public String getPassword() {
        return this.password;
    }
}
