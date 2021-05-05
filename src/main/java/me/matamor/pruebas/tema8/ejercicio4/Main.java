package me.matamor.pruebas.tema8.ejercicio4;

public class Main {

    public static void main(String[] args) {
        Punto puntoA = new Punto(1.5, 1.5);
        Punto puntoB = new Punto(2.5, 2.5);
        Punto puntoC = new Punto(5, 5);


        System.out.println(puntoA.visualizarPunto());
        System.out.println(puntoB.visualizarPunto());
        System.out.println(puntoC.visualizarPunto());

        System.out.printf("Distancia A > B: %.1f\n", puntoA.calcularDistancia(puntoB));
        System.out.printf("Distancia B > C: %.1f\n", puntoB.calcularDistancia(puntoC));
        System.out.printf("Distancia C > A: %.1f\n", puntoC.calcularDistancia(puntoA));
    }
}
