package me.matamor.pruebas.tema9.ejercicio9;

public class Ejercicio9 {

    public void ejecutar() throws EresTontoException, EresMuyTontoException, EresSuperTontoException {
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            throw new EresTontoException("Da error por dividir entre 0!");
        }

        try {
            String string = null;
            string.length();
        } catch (NullPointerException e) {
            throw new EresMuyTontoException("Da error porque string es null!");
        }

        try {
            int[] array = new int[0];
            array[0] = 10;
        } catch (IndexOutOfBoundsException e) {
            throw new EresSuperTontoException("Da error porque la posicion 0 no existe en el array!");
        }
    }

    public static void main(String[] args) {
        Ejercicio9 ejercicio9 = new Ejercicio9();
        try {
            ejercicio9.ejecutar();
        } catch (EresTontoException | EresMuyTontoException | EresSuperTontoException e) {
            e.printStackTrace();
        }
    }
}
