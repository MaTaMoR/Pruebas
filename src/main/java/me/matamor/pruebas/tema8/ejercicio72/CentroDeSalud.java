package me.matamor.pruebas.tema8.ejercicio72;

import me.matamor.pruebas.lib.Validate;

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
        Validate.ifTrue(this.contador >= this.pacientes.length, "No se pueden registrar más pacientes... Te toca morir!");

        this.pacientes[this.contador++] = paciente;
    }

    public void registrarPacientes(Paciente... pacientes) {
        for (Paciente paciente : pacientes) {
            registrarPaciente(paciente);
        }
    }

    public Paciente buscarPaciente(String sip) {
        return buscarPaciente(paciente -> paciente.getSip().equalsIgnoreCase(sip));
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

    public Atencion[] buscarAtenciones(CondicionBusqueda<Atencion> condicionBusqueda) {
        Atencion[] atenciones = atenciones();
        Atencion[] filtrado = new Atencion[atenciones.length];
        int contador = 0;

        for (Atencion atencion : atenciones) {
            if (condicionBusqueda.valido(atencion)) {
                filtrado[contador++] = atencion;
            }
        }

        return Arrays.copyOfRange(filtrado, 0, contador);
    }

    public Atencion[] atenciones() {
        int contador = 0;

        for (int i = 0; this.contador > i; i++) {
            Paciente paciente = this.pacientes[i];

            contador += paciente.getRegistroAtencion().getContador();
        }

        Atencion[] atenciones = new Atencion[contador];
        contador = 0;

        for (int i = 0; this.contador > i; i++) {
            Paciente paciente = this.pacientes[i];

            for (Atencion atencion : paciente.getRegistroAtencion().atenciones()) {
                atenciones[contador++] = atencion;
            }
        }

        return atenciones;
    }
}
