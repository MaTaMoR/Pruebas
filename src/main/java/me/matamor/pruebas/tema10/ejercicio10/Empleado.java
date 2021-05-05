package me.matamor.pruebas.tema10.ejercicio10;

import me.matamor.pruebas.lib.Input;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class Empleado {

    private final String documento;
    private final String nombre;
    private final String apellidos;
    private final Date fechaNacimiento;

    private final List<Hijo> hijos;

    private double sueldo;

    public Empleado(String documento, String nombre, String apellidos, Date fechaNacimiento, double sueldo) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;

        this.hijos = new ArrayList<>();

        this.sueldo = sueldo;
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

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int edad() {
        long now = System.currentTimeMillis();
        long difference = now - this.fechaNacimiento.getTime();

        return (int) TimeUnit.MILLISECONDS.toDays(difference) / Constantes.YEAR;
    }

    public void registrarHijo(Hijo hijo) {
        this.hijos.add(hijo);
    }

    public Hijo buscarHijo(String nombre) {
        for (Hijo hijo : this.hijos) {
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                return hijo;
            }
        }

        return null;
    }

    public boolean eliminarHijo(String nombre) {
        Hijo hijo = null;

        for (Hijo targetHijo : this.hijos) {
            if (targetHijo.getNombre().equalsIgnoreCase(nombre)) {
                hijo = targetHijo;
                break;
            }
        }

        if (hijo != null) {
            this.hijos.remove(hijo);
            return true;
        }

        return false;
    }

    public List<Hijo> hijos() {
        return this.hijos;
    }

    @Override
    public String toString() {
        return String.format("Documento: %s, Nombre: %s, Apellidos: %s, Fecha nacimiento: %s, Sueldo: %.2f",
                this.documento,
                this.nombre,
                this.apellidos,
                Input.dateFormat.format(this.fechaNacimiento),
                this.sueldo);
    }
}
