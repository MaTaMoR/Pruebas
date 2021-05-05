package me.matamor.pruebas.tema11.ejercicio6.multimedia;

import me.matamor.pruebas.tema11.ejercicio6.videoclub.Persona;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;

public class Pelicula extends Multimedia {

    private double duracion;
    private Persona actorPrincipal;
    private Persona actrizPersonal;

    public Pelicula(String titulo, Persona autor, Formato formato, int anyo, double duracion, Persona actorPrincipal, Persona actrizPrincipal) {
        super(titulo, autor, formato, anyo);

        this.duracion = duracion;
        this.actorPrincipal = actorPrincipal;
        this.actrizPersonal = actrizPrincipal;
    }

    public double getDuracion() {
        return this.duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Persona getActorPrincipal() {
        return this.actorPrincipal;
    }

    public void setActorPrincipal(Persona actorPrincipal) {
        this.actorPrincipal = actorPrincipal;
    }

    public Persona getActrizPersonal() {
        return this.actrizPersonal;
    }

    public void setActrizPersonal(Persona actrizPersonal) {
        this.actrizPersonal = actrizPersonal;
    }

    @Override
    public double precio() {
        double precio = super.precio();

        if (this.anyo < Constantes.ANYO_PELICULA_VIEJA) {
            precio -= Constantes.DESCUENTO_PELICULA_VIEJA;
        }

        return precio;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", formato=" + formato +
                ", anyo=" + anyo +
                ", duracion=" + duracion +
                ", actorPrincipal=" + actorPrincipal +
                ", actrizPersonal=" + actrizPersonal +
                '}';
    }
}
