package me.matamor.pruebas.tema11.ejercicio6.videoclub;

import me.matamor.pruebas.lib.Keyable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Poblacion implements Keyable<String> {

    private final String nombre;
    private final List<Socio> socios;

    public Poblacion(String nombre) {
        this.nombre = nombre;
        this.socios = new ArrayList<>();
    }

    @Override
    public String getKey() {
        return this.nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void registrarSocio(Socio socio) {
        if (!this.socios.contains(socio)) {
            this.socios.add(socio);
        }
    }

    public void borrarSocio(Socio socio) {
        this.socios.remove(socio);
    }

    public List<Socio> socios() {
        return this.socios;
    }

    @Override
    public String toString() {
        return "Poblacion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Poblacion) {
            Poblacion poblacion = (Poblacion) object;
            return Objects.equals(this.nombre, poblacion.nombre);
        }

        return false;
    }
}
