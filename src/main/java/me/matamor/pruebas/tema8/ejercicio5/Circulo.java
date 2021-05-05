package me.matamor.pruebas.tema8.ejercicio5;

import me.matamor.pruebas.tema8.ejercicio4.Punto;
import me.matamor.pruebas.lib.Validate;

public class Circulo {

    public static final int MIN_RADIO = 1;

    private Punto posicion;
    private double radio;

    public Circulo() {
        this(0, 0, 1);
    }

    public Circulo(double x, double y, double radio) {
        this(new Punto(x, y), radio);
    }

    public Circulo(Punto posicion, double radio) {
        Validate.notNull(posicion, "La posicion no puede ser null!");
        Validate.ifFalse(radio < MIN_RADIO, "El radio no puede ser menor que " + MIN_RADIO);

        this.posicion = posicion;
        this.radio = radio;
    }

    public Punto getPosicion() {
        return posicion;
    }

    public void setPosicion(Punto posicion) {
        Validate.notNull(posicion, "La posicion no puede ser null!");

        this.posicion = posicion;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        Validate.ifFalse(radio < MIN_RADIO, "El radio no puede ser menor que " + MIN_RADIO);

        this.radio = radio;
    }

    public double calcularDistancia(Punto punto) {
        return this.posicion.calcularDistancia(punto);
    }

    public double calcularDistancia(Circulo circulo) {
        Validate.notNull(circulo, "El circulo no puede ser null!");

        return calcularDistancia(circulo.getPosicion());
    }

    public double calcularArea() {
        return Math.PI * (this.radio * this.radio);
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * this.radio;
    }

    public String visualizarCirculo() {
        boolean centrado = this.posicion.getX() == 0 && this.posicion.getY() == 0;

        return String.format("Circulo de radio %.1f cm situado en el %s.", this.radio,
                (centrado ? "origen de las cordenadas" : "punto " + this.posicion.visualizarPunto()));
    }
}
