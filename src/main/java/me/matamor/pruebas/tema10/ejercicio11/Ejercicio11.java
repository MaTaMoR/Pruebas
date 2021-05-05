package me.matamor.pruebas.tema10.ejercicio11;

import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

public class Ejercicio11 extends MenuConsola {

    private final Escuela escuela;

    private final Registracciones registracciones;
    private final Consultas consultas;
    private final Datos datos;

    public Ejercicio11() {
        super("Escuela");

        this.escuela = new Escuela();
        this.registracciones = new Registracciones(this.escuela);
        this.consultas = new Consultas(this.escuela);
        this.datos = new Datos(this.escuela);

        registrarOpcion(Constantes.REGISTRACCIONES, new OpcionSimple("Registracciones", "Registra aulas, profesores, asignaturas, grupos y alumnos", this::registracciones));
        registrarOpcion(Constantes.CONSULTAS, new OpcionSimple("Consultas", "Consulta información de la escuela", this::consultas));
        registrarOpcion(Constantes.DATOS, new OpcionSimple("Datos", "Listado de los datos de la escuela", this::datos));
    }

    private void registracciones() {
        this.registracciones.ejecutar();
    }

    private void consultas() {
        this.consultas.ejecutar();
    }

    private void datos() {
        this.datos.ejecutar();
    }

    public static void main(String[] args) {
        Ejercicio11 ejercicio11 = new Ejercicio11();
        ejercicio11.ejecutar();
    }
}
