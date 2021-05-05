package me.matamor.pruebas.tema8.ejercicio7;

import java.util.Arrays;

public class CentroDeSalud {

    private static final int DIAS = 31;
    private static final int PACIENTES_DIARIOS = 40;

    private final Paciente[] pacientes;
    private int contador;

    public CentroDeSalud() {
        this.pacientes = new Paciente[DIAS * PACIENTES_DIARIOS];
        this.contador = 0;
    }

    public boolean permiteMasPacientes() {
        return this.contador < this.pacientes.length;
    }

    public void registrarPaciente(Paciente paciente) {
        if (this.contador >= this.pacientes.length) {
            throw new IllegalStateException("No se pueden registrar más pacientes!");
        }

        this.pacientes[this.contador++] = paciente;
    }

    public void registrarPacientes(Paciente... pacientes) {
        for (Paciente paciente : pacientes) {
            registrarPaciente(paciente);
        }
    }
    public Paciente buscarPaciente(String sip, Paciente.Estado estado) {
        return buscarPaciente(paciente -> paciente.getSip().equalsIgnoreCase(sip) && paciente.getEstado() == estado);
    }

    public Paciente buscarPaciente(CondicionBusqueda<Paciente> condicionBusqueda) {
        for (int i = 0; this.contador > i; i++) {
            Paciente paciente = this.pacientes[i];

            if (condicionBusqueda.valido(paciente)) {
                return paciente;
            }
        }

        return null;
    }

    public Paciente[] buscarPaciente(String sip) {
        return buscarPacientes(paciente -> paciente.getSip().equalsIgnoreCase(sip));
    }

    public Paciente[] buscarPaciente(String sip, Paciente.Estado... estados) {
        return buscarPacientes(value -> {
            if (value.getSip().equalsIgnoreCase(sip)) {
                for (Paciente.Estado estado : estados) {
                    if (value.getEstado() == estado) {
                        return true;
                    }
                }
            }

            return false;
        });
    }

    public Paciente[] buscarPacientes(Paciente.Estado estado) {
        return buscarPacientes(paciente -> paciente.getEstado() == estado);
    }

    public Paciente[] buscarPacientes(CondicionBusqueda<Paciente> condicionBusqueda) {
        Paciente[] pacientes = new Paciente[this.contador];
        int contador = 0;

        for (int i = 0; this.contador > i; i++) {
            Paciente paciente = this.pacientes[i];

            if (condicionBusqueda.valido(paciente)) {
                pacientes[contador++] = paciente;
            }
        }

        return Arrays.copyOfRange(pacientes, 0, contador);
    }

    public Paciente[] pacientes() {
        return Arrays.copyOfRange(this.pacientes, 0, this.contador);
    }


}
