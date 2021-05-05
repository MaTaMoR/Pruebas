package me.matamor.pruebas.tema8.ejercicio72;

import me.matamor.pruebas.lib.Validate;

public class Atencion {

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

    private final Paciente paciente;

    private final long fechaEntrada;
    private final String sintomatologia;

    private double[] constantesVitales;

    private String alta;
    private long fechaSalida;

    private Estado estado;

    public Atencion(Paciente paciente, String sintomatologia) {
        this.paciente = paciente;
        this.fechaEntrada = System.currentTimeMillis();
        this.sintomatologia = sintomatologia;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public long getFechaEntrada() {
        return fechaEntrada;
    }

    public String getSintomatologia() {
        return sintomatologia;
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

    public Atencion registrarConstantes(double temperatura, double pulsaciones, double tensionSis, double tensionDia) {
        if (this.constantesVitales == null) {
            this.constantesVitales = new double[4];

            this.constantesVitales[ConstanteVital.TEMPERATURA.ordinal()] = temperatura;
            this.constantesVitales[ConstanteVital.PULSACIONES.ordinal()] = pulsaciones;
            this.constantesVitales[ConstanteVital.TENSION_SIS.ordinal()] = tensionSis;
            this.constantesVitales[ConstanteVital.TENSION_DIA.ordinal()] = tensionDia;

            setEstado(Estado.ATENDIENDO);
        }

        return this;
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

    public Atencion registrarAlta(String alta) {
        if (this.alta == null) {
            this.alta = alta;
            this.fechaSalida = System.currentTimeMillis();

            setEstado(Estado.ALTA);
        }

        return this;
    }

    public String getAlta() {
        return alta;
    }

}
