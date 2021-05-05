package me.matamor.pruebas.tema11.ejercicio7.estadio;

import me.matamor.pruebas.lib.Fecha;

import java.util.Objects;

public class Partido {

    private final Estadio estadio;

    private final Fecha fechaPartido;
    private final TipoPartido tipoPartido;

    private final Equipo equipoLocal;
    private final Equipo equipoVisitante;

    private final RegistroEntradas registroEntradas;

    public Partido(Estadio estadio, Fecha fechaPartido, TipoPartido tipoPartido, Equipo equipoLocal, Equipo equipoVisitante) {
        this.estadio = estadio;
        this.tipoPartido = tipoPartido;
        this.fechaPartido = fechaPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;

        this.registroEntradas = new RegistroEntradas(this);
    }

    public Estadio getEstadio() {
        return this.estadio;
    }

    public Fecha getFechaPartido() {
        return this.fechaPartido;
    }

    public TipoPartido getTipoPartido() {
        return this.tipoPartido;
    }

    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public RegistroEntradas getRegistroEntradas() {
        return this.registroEntradas;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "estadio=" + estadio +
                ", fechaPartido=" + fechaPartido +
                ", tipoPartido=" + tipoPartido +
                ", equipoLocal=" + equipoLocal +
                ", equipoVisitante=" + equipoVisitante +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partido partido = (Partido) o;
        return tipoPartido == partido.tipoPartido &&
                Objects.equals(fechaPartido, partido.fechaPartido) &&
                Objects.equals(equipoLocal, partido.equipoLocal) &&
                Objects.equals(equipoVisitante, partido.equipoVisitante);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.fechaPartido);
    }
}
