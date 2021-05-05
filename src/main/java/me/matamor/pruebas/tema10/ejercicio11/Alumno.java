package me.matamor.pruebas.tema10.ejercicio11;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Alumno {

    private final int identificador;
    private final String nombre;

    private Grupo grupo;

    private final List<Asignatura> asignaturas;

    public Alumno(int identificador, String nombre, Grupo grupo, List<Asignatura> asignaturas) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.grupo = grupo;
        this.asignaturas = new ArrayList<>(asignaturas);
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public boolean nuevaAsignatura(Asignatura asignatura) {
        return this.asignaturas.add(asignatura);
    }

    public boolean borrarAsignatura(Asignatura asignatura) {
        return this.asignaturas.remove(asignatura);
    }

    public List<Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.identificador);
    }

    @Override
    public String toString() {
        List<String> asignaturas = this.asignaturas.stream().map(Asignatura::getNombre).collect(Collectors.toList());
        return String.format("Id: %d, Nombre: %s, Asignaturas: %s", this.identificador, this.nombre, asignaturas);
    }
}
