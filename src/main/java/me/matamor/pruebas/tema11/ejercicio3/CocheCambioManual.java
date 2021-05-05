package me.matamor.pruebas.tema11.ejercicio3;

public class CocheCambioManual extends Coche {

    public CocheCambioManual(String matricula) {
        super(matricula, new int[] { 20, 50, 80, 120, 180 });
    }

    @Override
    public void cambiarMarcha(int marcha) throws CocheException {
        super.cambiarMarcha(marcha);
    }
}
