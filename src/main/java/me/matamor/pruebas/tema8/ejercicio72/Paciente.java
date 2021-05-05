package me.matamor.pruebas.tema8.ejercicio72;

import java.text.SimpleDateFormat;

public class Paciente {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy");

    //Si, binario
    public enum Sexo {

        VARON,
        MUJER

    }

    private String sip;
    private String nombre;
    private Sexo sexo;
    private int edad;

    private final RegistroAtencion registroAtencion;

    public Paciente(String sip, String nombre, Sexo sexo, int edad) {
        this.sip = sip;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;

        this.registroAtencion = new RegistroAtencion();
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public RegistroAtencion getRegistroAtencion() {
        return registroAtencion;
    }
}
