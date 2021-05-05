package me.matamor.pruebas.tema8.ejercicio3;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Alumno {

    private int nia;
    private String nombre;
    private String apellidos;
    private GregorianCalendar fechaNacimiento;
    private String grupo;
    private String telefono;

    public Alumno(int nia, String nombre, String apellidos, GregorianCalendar fechaNacimiento, String grupo, String telefono) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.grupo = grupo;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "Alumno{" +
                "nia=" + nia +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + formateador.format(fechaNacimiento.getTime()) +
                " (" + getEdad() +" años)" +
                ", grupo='" + grupo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public int getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        int anyo = fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaNacimiento.get(Calendar.MONTH) + 1;
        int dia = fechaNacimiento.get(Calendar.DAY_OF_MONTH);
        LocalDate cumple = LocalDate.of(anyo, mes, dia);
        Period periodo = Period.between(cumple, hoy);
        return periodo.getYears();
    }
}
