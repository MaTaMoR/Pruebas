package me.matamor.pruebas.tema12.ejercicio15;

public class ResultadoComparacion {

    private final String nombre;
    private final Resultado resultado;

    public ResultadoComparacion(String nombre, Resultado resultado) {
        this.nombre = nombre;
        this.resultado = resultado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Resultado getResultado() {
        return this.resultado;
    }

    public enum Resultado {

        IGUALES,
        FALTA_EN_1,
        FALTA_EN_2,
        MAS_NUEVO_EN_1,
        MAS_NUEVO_EN_2

    }
}
