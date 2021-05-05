package me.matamor.pruebas.tema11.ejercicio6.videoclub;

import me.matamor.pruebas.lib.Keyable;
import me.matamor.pruebas.tema11.ejercicio6.multimedia.Multimedia;

import java.util.*;

public class Persona implements Keyable<String> {

    protected final String nombre;
    protected final String apellidos;

    private final Map<Relacion, List<Multimedia>> relaciones;

    public Persona(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.relaciones = new HashMap<>();
    }

    @Override
    public String getKey() {
        return this.nombre + "-" + this.apellidos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void nuevaRelacion(Relacion relacion, Multimedia multimedia) {
        List<Multimedia> listaMultimedia = this.relaciones.computeIfAbsent(relacion, (k) -> new ArrayList<>());
        listaMultimedia.add(multimedia);
    }

    public void borrarRelacion(Relacion relacion, Multimedia multimedia) {
        List<Multimedia> listaMultimedia = this.relaciones.get(relacion);
        if (listaMultimedia == null) return;

        listaMultimedia.remove(multimedia);

        if (listaMultimedia.isEmpty()) {
            this.relaciones.remove(relacion);
        }
    }

    public Map<Relacion, List<Multimedia>> relaciones() {
        return relaciones;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object instanceof Persona) {
            Persona persona = (Persona) object;
            return Objects.equals(this.nombre, persona.nombre)
                    && Objects.equals(this.apellidos, persona.apellidos);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
