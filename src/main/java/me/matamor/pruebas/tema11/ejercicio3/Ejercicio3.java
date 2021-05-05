package me.matamor.pruebas.tema11.ejercicio3;

import me.matamor.pruebas.lib.Input;
import me.matamor.pruebas.lib.ejercicio.MenuConsola;
import me.matamor.pruebas.lib.ejercicio.OpcionSimple;

public class Ejercicio3 extends MenuConsola {

    private final RegistroCoches registroCoches;

    public Ejercicio3() {
        super("Coches");

        this.registroCoches = new RegistroCoches();

//        Coche manual = new CocheCambioManual("5783 CKR");
//        Coche automatico = new CocheCambioAutomatico("1234 EKR");
//
//        this.registroCoches.registrarCoche(manual);
//        this.registroCoches.registrarCoche(automatico);

        registrarOpcion(Constantes.CREAR_COCHE, new OpcionSimple("Crear coche", "Crear un coche nuevo", this::crearCoche));
    }

    private void crearCoche() {
        System.out.print("Introduce la matricula del coche: ");
        String matricula = Input.leerLinea(Constantes.LONGITUD_MATRICULA, Constantes.LONGITUD_MATRICULA);

        Coche coche = this.registroCoches.buscarCoche(matricula);
        if (coche == null) {

            System.out.println("Es el coche manual ? (si/no) ");
            boolean respuesta = Input.leerPregunta();

            if (respuesta) {
                coche = new CocheCambioManual(matricula);
            } else {
                coche = new CocheCambioAutomatico(matricula);
            }

            this.registroCoches.registrarCoche(coche);

            System.out.println("Coche: " + coche);

            coche.acelerar(60);

            if (coche instanceof CocheCambioManual) {
                ((CocheCambioManual) coche).cambiarMarcha(2);
            }

            System.out.println("Coche: " + coche);
        } else {
            System.out.println("Ya hay un coche registrado con esa matricula!");
        }
    }

    public static void main(String[] args) {
        Ejercicio3 ejercicio3 = new Ejercicio3();
        ejercicio3.ejecutar();
    }
}
