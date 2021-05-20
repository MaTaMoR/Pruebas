package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.Mesa;
import me.matamor.pruebas.blackjack.jugadores.controlador.Controlador;
import me.matamor.pruebas.blackjack.jugadores.controlador.ControladorBot;

public abstract class Jugador {

    protected final String nombre;
    protected final Mazo mazo;

    protected int saldo;
    protected int apuesta;

    protected int manosGanadas;

    protected Estado estado;
    protected boolean doblarApuesta;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new Mazo();

        this.saldo = Constantes.SALDO_DEFAULT;
        this.apuesta = Constantes.APUESTA_DEFAULT;

        this.manosGanadas = 0;
        this.estado = Estado.ACTIVO;
        this.doblarApuesta = false;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Mazo getMazo() {
        return this.mazo;
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
