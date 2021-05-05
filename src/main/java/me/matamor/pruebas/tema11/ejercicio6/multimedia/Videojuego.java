package me.matamor.pruebas.tema11.ejercicio6.multimedia;

import me.matamor.pruebas.tema11.ejercicio6.videoclub.Persona;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;

public class Videojuego extends Multimedia {

    private Plataforma plataforma;

    public Videojuego(String titulo, Persona autor, Formato formato, int anyo, Plataforma plataforma) {
        super(titulo, autor, formato, anyo);

        this.plataforma = plataforma;
    }

    public Plataforma getPlataforma() {
        return this.plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public double precio() {
        double precio = super.precio();

        if (this.anyo < Constantes.ANYO_VIDEOJUEGO_VIEJO) {
            precio -= Constantes.DESCUENTO_VIDEOJUEGO_VIEJO;
        }

        return precio;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", formato=" + formato +
                ", anyo=" + anyo +
                ", plataforma=" + plataforma +
                '}';
    }
}
