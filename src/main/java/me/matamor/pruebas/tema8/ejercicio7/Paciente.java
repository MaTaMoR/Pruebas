package me.matamor.pruebas.tema8.ejercicio7;

import me.matamor.pruebas.lib.Validate;

import java.text.SimpleDateFormat;

public class Paciente {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy");

    //Si, binario
    public enum Sexo {

        VARON,
        MUJER

    }

    public enum Estado {

        ESPERANDO,
        ATENDIENDO,
        ALTA

    }

    public enum ConstanteVital {

        TEMPERATURA,
        PULSACIONES,
        TENSION_SIS,
        TENSION_DIA

    }

    private String sip;
    private String nombre;
    private Sexo sexo;
    private int edad;
    private long fechaEntrada;
    private String sintomatologia;

    private double[] constantesVitales;

    private String alta;
    private long fechaSalida;

    private Estado estado;

    public Paciente(String sip, String nombre, Sexo sexo, int edad, String sintomatologia) {
        this.sip = sip;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.fechaEntrada = System.currentTimeMillis();
        this.estado = Estado.ESPERANDO;
        this.sintomatologia = sintomatologia;
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

    public long getFechaEntrada() {
        return this.fechaEntrada;
    }

    public void setFechaEntrada(long fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getSintomatologia() {
        return sintomatologia;
    }

    public String getAlta() {
        return alta;
    }

    public Estado getEstado() {
        return this.estado;
    }

    private void setEstado(Estado estado) {
        this.estado = estado;
    }

    public long getFechaSalida() {
        return fechaSalida;
    }

    public boolean tieneConstantes() {
        return this.constantesVitales != null;
    }

    public void registrarConstantes(double temperatura, double pulsaciones, double tensionSis, double tensionDia) {
        if (this.constantesVitales == null) {
            this.constantesVitales = new double[4];

            this.constantesVitales[ConstanteVital.TEMPERATURA.ordinal()] = temperatura;
            this.constantesVitales[ConstanteVital.PULSACIONES.ordinal()] = pulsaciones;
            this.constantesVitales[ConstanteVital.TENSION_SIS.ordinal()] = tensionSis;
            this.constantesVitales[ConstanteVital.TENSION_DIA.ordinal()] = tensionDia;

            setEstado(Estado.ATENDIENDO);
        }
    }

    public double getConstanteVital(ConstanteVital constanteVital) {
        Validate.notNull(this.constantesVitales, "Las constantes vitales no han sido registradas!");

        return this.constantesVitales[constanteVital.ordinal()];
    }

    public double[] getConstantesVitales() {
        Validate.notNull(this.constantesVitales, "Las constantes vitales no han sido registradas!");

        return this.constantesVitales;
    }

    public boolean tieneAlta() {
        return this.alta != null;
    }

    public void registrarAlta(String alta) {
        if (this.alta == null) {
            this.alta = alta;
            this.fechaSalida = System.currentTimeMillis();

            setEstado(Estado.ALTA);
        }
    }
}
