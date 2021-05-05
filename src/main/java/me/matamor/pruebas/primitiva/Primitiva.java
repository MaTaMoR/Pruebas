package me.matamor.pruebas.primitiva;

public class Primitiva {

    private final GeneradorCombinacion generador;

    private Combinacion combinacionGanadora;

    public Primitiva() {
        this.generador = new GeneradorCombinacion();
    }

    public Combinacion getCombinacionGanadora() throws PrimitivaException {
        if (this.combinacionGanadora == null) {
            throw new PrimitivaException("La combinación ganadora todavía no ha sido generada!");
        }

        return this.combinacionGanadora;
    }

    public Premio comparar(Combinacion combinacion) throws PrimitivaException {
        Combinacion combinacionGanadora = getCombinacionGanadora();

        return combinacionGanadora.comparar(combinacion);
    }

    public Combinacion generarCombinacion() {
        return this.generador.generar();
    }

    public void generarCombinacionGanadora() {
        this.combinacionGanadora = this.generador.generar();
    }

    public void reiniciar() {
        this.combinacionGanadora = null;
    }
}
