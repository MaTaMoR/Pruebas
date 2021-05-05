package me.matamor.pruebas.tema10.ejercicio10;

import java.util.*;

public class Persona {

    public enum Relacion {

        HIJO {
            @Override
            public Relacion opuesto() {
                return PADRE;
            }
        },
        PADRE {
            @Override
            public Relacion opuesto() {
                return HIJO;
            }
        };

        public abstract Relacion opuesto();
    }

    private final String documento;
    private final String nombre;
    private final String apellidos;
    private final Date fechaNacimiento;

    private final Map<Relacion, List<Persona>> relaciones;

    public Persona(String documento, String nombre, String apellidos, Date fechaNacimiento) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;

        this.relaciones = new HashMap<>();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void registrarRelacion(Relacion relacion, Persona persona) {
        List<Persona> personas = this.relaciones.computeIfAbsent(relacion, k -> new ArrayList<>());
        personas.add(persona);

        persona.registrarRelacion(relacion.opuesto(), this);
    }

    public void eliminarRelacion(Relacion relacion, Persona persona) {
        List<Persona> personas = this.relaciones.get(relacion);
        if (personas != null) {
            personas.remove(persona);

            if (personas.isEmpty()) {
                this.relaciones.remove(relacion);
            }

            persona.eliminarRelacion(relacion.opuesto(), this);
        }
    }
}
