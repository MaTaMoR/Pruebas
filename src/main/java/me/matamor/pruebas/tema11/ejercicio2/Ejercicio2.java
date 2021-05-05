package me.matamor.pruebas.tema11.ejercicio2;

import me.matamor.pruebas.tema11.ejercicio1.Punto;

import java.util.Arrays;

public class Ejercicio2 {

    public static void main(String[] args) {
        Poligono poligono = new Poligono(Arrays.asList(
                new Punto(0, 0),
                new Punto(2, 0),
                new Punto(2, 2),
                new Punto(0,2)));

        System.out.println(poligono);

        System.out.println("Puntos: " + poligono.numVertex());
        System.out.println("Perimetro: " + poligono.perimetro());

        System.out.println("Moviendo poligono...");

        poligono.traslada(4, -3);

        System.out.println("Puntos: " + poligono.numVertex());
        System.out.println("Perimetro: " + poligono.perimetro());
    }
}
