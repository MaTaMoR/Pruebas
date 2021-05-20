package me.matamor.pruebas.tema12.ejercicio16;

public class Ejercicio16 {

    public static void main(String[] args) {
        GestorTabla gestorTabla = new GestorTabla();

        Tabla tabla = gestorTabla.getTabla();
        tabla.print();

        tabla.jugar(0, 0, Ficha.O);
        tabla.jugar(0, 1, Ficha.X);

        gestorTabla.guardar();
    }
}
