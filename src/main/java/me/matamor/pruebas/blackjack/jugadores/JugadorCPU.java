package me.matamor.pruebas.blackjack.jugadores;

import me.matamor.pruebas.blackjack.configuracion.Constantes;

public class JugadorCPU extends Jugador {

    public JugadorCPU() {
        super("CPU");

        setSaldo(Constantes.SALDO_CPU_DEFAULT);
    }
}
