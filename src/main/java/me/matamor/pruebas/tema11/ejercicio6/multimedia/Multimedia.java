package me.matamor.pruebas.tema11.ejercicio6.multimedia;

import me.matamor.pruebas.lib.Keyable;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.Persona;
import me.matamor.pruebas.tema11.ejercicio6.videoclub.RegistroAlquiler;
import me.matamor.pruebas.tema11.ejercicio6.constantes.Constantes;

import java.util.Objects;

public class Multimedia implements Keyable<String> {

    protected String titulo;
    protected Persona autor;
    protected Formato formato;
    protected int anyo;

    protected RegistroAlquiler registroAlquiler;

    public Multimedia(String titulo, Persona autor, Formato formato, int anyo) {
        this.titulo = titulo;
        this.autor = autor;
        this.formato = formato;
        this.anyo = anyo;
    }

    @Override
    public String getKey() {
        return this.titulo + ":" + this.autor.getKey();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Persona getAutor() {
        return this.autor;
    }

    public Formato getFormato() {
        return this.formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public int getAnyo() {
        return this.anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public double precio() {
        return Constantes.PRECIO_BASE;
    }

    public boolean tieneRegistroAlquiler() {
        return this.registroAlquiler != null;
    }

    public RegistroAlquiler getRegistroAlquiler() {
        return this.registroAlquiler;
    }

    public void setRegistroAlquiler(RegistroAlquiler registroAlquiler) {
        this.registroAlquiler = registroAlquiler;
    }

    public boolean equals(Multimedia multimedia) {
        if (multimedia == this) {
            return true;
        } else if (multimedia != null) {
            return Objects.equals(this.titulo, multimedia.titulo) && Objects.equals(this.autor, multimedia.autor);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", formato=" + formato +
                ", anyo=" + anyo +
                '}';
    }
}
