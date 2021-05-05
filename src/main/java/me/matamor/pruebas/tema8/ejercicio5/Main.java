package me.matamor.pruebas.tema8.ejercicio5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Circulo circuloA = new Circulo(0, 0, 1.5);
        Circulo circuloB = new Circulo(5, 10, 5);
        Circulo circuloC = new Circulo(10, 10, 20);

        for (Circulo circulo : Arrays.asList(circuloA, circuloB, circuloC)) {
            System.out.printf("Posicion=%s, Perimetro=%.1f, Area=%.1f\n",
                    circulo.getPosicion().visualizarPunto(),
                    circuloA.calcularPerimetro(),
                    circuloA.calcularArea());
        }

        System.out.printf("Distancia A > B: %.1f\n", circuloA.calcularDistancia(circuloB));
        System.out.printf("Distancia B > C: %.1f\n", circuloB.calcularDistancia(circuloC));
        System.out.printf("Distancia C > A: %.1f\n", circuloC.calcularDistancia(circuloA));
    }
}
