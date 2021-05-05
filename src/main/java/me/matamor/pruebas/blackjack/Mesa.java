package me.matamor.pruebas.blackjack;

import me.matamor.pruebas.blackjack.jugadores.Jugador;
import me.matamor.pruebas.blackjack.jugadores.JugadorCPU;

import java.util.List;

public class Mesa {

    private final Mazo mazo;
    private final JugadorCPU cpu;

    private final List<Jugador> jugadores;

    private int manos;

    public Mesa(List<Jugador> jugadores) {
        this.mazo = Baraja.nuevoMazo();
        this.cpu = new JugadorCPU();

        this.jugadores = jugadores;

        this.manos = 0;
    }

    public JugadorCPU getCpu() {
        return this.cpu;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public int getManos() {
        return this.manos;
    }

    private void iniciar() {
        boolean activo = true;

        this.manos = 1;
        this.mazo.mezclar();

        do {
            for (Jugador jugador : this.jugadores) {
                for (int i = 0; Constantes.CARTAS_RONDA > i; i++) {
                    Carta carta = this.mazo.sacarCarta();
                    jugador.getMazo().nuevaCarta(carta);
                }
            }


        } while (activo);
    }
}
