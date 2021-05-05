package me.matamor.pruebas.tema11.ejercicio7.estadio;

import me.matamor.pruebas.lib.Condicion;
import me.matamor.pruebas.lib.Fecha;
import me.matamor.pruebas.tema11.ejercicio7.exceptions.PartidoException;

import java.util.*;
import java.util.stream.Collectors;

public class Estadio {

    private final Map<Fecha, Partido> partidos;

    private final int zonas;
    private final int filas;
    private final int asientos;

    public Estadio(int zonas, int filas, int asientos) {
        this.zonas = zonas;
        this.filas = filas;
        this.asientos = asientos;

        this.partidos = new LinkedHashMap<>();
    }

    /**
     * @return la cantidad de Zonas
     */

    public int getZonas() {
        return this.zonas;
    }

    /**
     * @return las filas de cada Zona
     */

    public int getFilas() {
        return this.filas;
    }

    /**
     * @return la cantidad de asientos de cada fila de cada Zona
     */

    public int getAsientos() {
        return this.asientos;
    }

    /**
     * Busca la información de un partido según su fecha
     * @param fecha la fecha del partido
     * @return la información del partido
     */

    public Partido buscarPartido(Fecha fecha) {
        return this.partidos.get(fecha);
    }

    /**
     * Busca si hay un partido en al fecha definida
     * @param fecha la fecha del partido
     * @return true si hay un partido registrado para esa fecha
     */

    public boolean hayPartido(Fecha fecha) {
        return this.partidos.containsKey(fecha);
    }

    /**
     * Crea un partido para la fecha definida
     * @param fecha la fecha del partido
     * @param tipoPartido el tipo de partido
     * @param equipoLocal el equipo local del partido
     * @param equipoVisitante el equipo visitante del partido
     * @return el Partido creado y registrado
     * @throws PartidoException si ya hay un partido registrado para esa fecha!
     */

    public Partido registrarPartido(Fecha fecha, TipoPartido tipoPartido, Equipo equipoLocal, Equipo equipoVisitante) throws PartidoException {
        if (hayPartido(fecha)) {
            throw new PartidoException("Ya hay un partido registrado para la fecha: " + fecha);
        }

        Partido partido = new Partido(this, fecha, tipoPartido, equipoLocal, equipoVisitante);
        this.partidos.put(fecha, partido);

        return partido;
    }

    /**
     * Devuelve un listado con los partidos que cumplen la condicion
     * @param condicion la condicion para buscar los partidos
     * @return el listado de partidos que cumplen la condicion
     */

    public List<Partido> buscarPartidos(Condicion<Partido> condicion) {
        return this.partidos.values().stream().filter(condicion::comprobar).collect(Collectors.toList());
    }

    /**
     * Devuelve todos los partidos registrados
     * @return collection de todos los partidos
     */

    public Collection<Partido> partidos() {
        return this.partidos.values();
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "zonas=" + zonas +
                ", filas=" + filas +
                ", asientos=" + asientos +
                '}';
    }
}
