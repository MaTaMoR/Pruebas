package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.controlador.Controlador;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorBot;

public class Jugador {

    private final String nombre;
    private final Controlador controlador;

    private Mazo mazo;

    private int saldo;
    private int apuesta;

    private int manosGanadas;

    private Estado estado;
    private boolean doblarApuesta;

    public Jugador(String nombre, Controlador controlador) {
        this(nombre, controlador, new Mazo());
    }

    public Jugador(String nombre, Controlador controlador, Mazo mazo) {
        this.nombre = nombre;
        this.controlador = controlador;
        this.mazo = mazo;

        this.saldo = Constantes.SALDO_DEFAULT;
        this.apuesta = Constantes.APUESTA_DEFAULT;

        this.manosGanadas = 0;
        this.estado = Estado.ACTIVO;
        this.doblarApuesta = false;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Controlador getControlador() {
        return this.controlador;
    }

    public Mazo getMazo() {
        return this.mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getApuesta() {
        return this.apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public int getManosGanadas() {
        return this.manosGanadas;
    }

    public void setManosGanadas(int manosGanadas) {
        this.manosGanadas = manosGanadas;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isDoblarApuesta() {
        return this.doblarApuesta;
    }

    public void setDoblarApuesta(boolean doblarApuesta) {
        this.doblarApuesta = doblarApuesta;
    }

    public enum Estado {

        PIERDE,
        GANA,
        ACTIVO,
        EMPATE,
        PLANTADO,
        FUERA

    }
}
