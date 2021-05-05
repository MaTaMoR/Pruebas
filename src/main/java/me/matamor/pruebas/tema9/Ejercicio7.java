package me.matamor.pruebas.tema9;

public class Ejercicio7 {

    public void ejecutar() {
        String[] array = new String[] { "hola", "que", null, "tal", "?" };
        mostrarCadenasArray(array);
    }

    private void mostrarCadenasArray(String[] array) {
        if (array != null) {
            for (String string : array) {
                if (string != null && !string.isEmpty()) {
                    System.out.println(string.charAt(0));
                }
            }
        }
    }

    public static void main(String[] args) {
        Ejercicio7 ejercicio7 = new Ejercicio7();
        ejercicio7.ejecutar();
    }
}
