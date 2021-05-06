package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;
import me.matamor.pruebas.blackjack.cartas.Mazo;
import me.matamor.pruebas.blackjack.juego.Mesa;

public abstract class Jugador {

    private final String nombre;
    private final Mazo mazo;

    private int saldo;
    private int apuesta;

    private int manosGanadas;

    private Estado estado;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new Mazo();

        this.saldo = Constantes.DEFAULT_BALANCE;
        this.apuesta = Constantes.DEFAULT_BET;

        this.manosGanadas = 0;
        this.estado = Estado.ACTIVO;
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

    public abstract Respuesta jugar(Mesa mesa);

    public enum Respuesta {

        SALTAR,
        PEDIR_CARTA

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
