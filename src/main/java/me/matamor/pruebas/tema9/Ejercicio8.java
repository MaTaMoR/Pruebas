package me.matamor.pruebas.tema9;

public class Ejercicio8 {

    public void ejecutar() {
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Da error por dividir entre 0!");
        }

        try {
            String string = null;
            string.length();
        } catch (NullPointerException e) {
            System.out.println("Da error porque string es null!");
        }

        try {
            int[] array = new int[0];
            array[0] = 10;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Da error porque la posicion 0 no existe en el array!");
        }
    }

    public static void main(String[] args) {
        Ejercicio8 ejercicio8 = new Ejercicio8();
        ejercicio8.ejecutar();
    }
}
