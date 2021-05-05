package me.matamor.pruebas.tema11.ejercicio6.videoclub;

import me.matamor.pruebas.lib.Keyable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Socio extends Persona implements Keyable<String> {

    private final String nif;
    private final Date fechaNacimiento;
    private final Poblacion poblacion;

    private final List<RegistroAlquiler> registroAlquileres;

    public Socio(String nombre, String apellidos, String nif, Date fechaNacimiento, Poblacion poblacion) {
        super(nombre, apellidos);

        this.nif = nif;
        this.fechaNacimiento = fechaNacimiento;
        this.poblacion = poblacion;

        this.registroAlquileres = new ArrayList<>();
    }

    @Override
    public String getKey() {
        return this.nif;
    }

    public String getNif() {
        return this.nif;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public Poblacion getPoblacion() {
        return this.poblacion;
    }

    public boolean tieneAlquileres() {
        return this.registroAlquileres.size() > 0;
    }

    public void nuevoAlquiler(RegistroAlquiler registroAlquiler) {
        if (!this.registroAlquileres.contains(registroAlquiler)) {
            this.registroAlquileres.add(registroAlquiler);
        }
    }

    public void borrarAlquiler(RegistroAlquiler registroAlquiler) {
        this.registroAlquileres.remove(registroAlquiler);
    }

    public List<RegistroAlquiler> registroAlquileres() {
        return this.registroAlquileres;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", poblacion=" + poblacion +
                ", registroAlquileres=" + registroAlquileres +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Socio) {
            Socio socio = (Socio) object;
            return Objects.equals(this.nif, socio.nif);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.nif);
    }
}
