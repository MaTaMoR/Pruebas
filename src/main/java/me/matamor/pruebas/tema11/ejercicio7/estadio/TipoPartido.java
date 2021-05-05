package me.matamor.pruebas.tema11.ejercicio7.estadio;

public enum TipoPartido {

    BAJA_AFLUENCIA(0.75),
    MEDIA_AFLUENCIA(1),
    ALTA_AFLUENCIA(1.30);

    private final double multiplicador;

    TipoPartido(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return this.multiplicador;
    }
}
